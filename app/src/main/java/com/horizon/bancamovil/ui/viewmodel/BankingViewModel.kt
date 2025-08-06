package com.horizon.bancamovil.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.horizon.bancamovil.data.local.entities.BankingEntity
import com.horizon.bancamovil.domain.model.BankContacts
import com.horizon.bancamovil.domain.state.DataUser
import com.horizon.bancamovil.domain.state.DepositState
import com.horizon.bancamovil.domain.events.BankingEvents
import com.horizon.bancamovil.data.repository.BankingRepo
import com.horizon.bancamovil.data.repository.PreferencesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BankingViewModel @Inject constructor(
    private val repo: BankingRepo,
    private val sharedPreferencesRepo: PreferencesRepo
) : ViewModel() {

    private val _operationState = MutableStateFlow(DepositState())
    val operationState: StateFlow<DepositState> = _operationState.asStateFlow()

    private val _accountState = MutableStateFlow(DataUser())
    val accountState: StateFlow<DataUser> = _accountState.asStateFlow()

    init {
        getAllBankingData()
        getModeTheme()
    }

    fun events(events: BankingEvents) {
        when (events) {
            is BankingEvents.OnValueChange -> {
                val cleanedText = events.value.filter { it.isDigit() }

                _operationState.value = _operationState.value.copy(
                    rawText = cleanedText
                )
            }

            is BankingEvents.DepositInEvent -> {

                val valueAccount = _accountState.value.valueAccount

                viewModelScope.launch {
                    repo.deposit((valueAccount + events.value).toInt())
                }
            }

            is BankingEvents.AvailableSitesDeposit -> {
                _operationState.value = _operationState.value.copy(
                    depositInSites = events.index
                )
            }

            is BankingEvents.WithdrawEvent -> {

                val valueAccount = _accountState.value.valueAccount

                viewModelScope.launch {
                    repo.withdraw((valueAccount - events.amount).toInt())

                }
            }

            is BankingEvents.SelectedAddress -> {
                viewModelScope.launch {
                    repo.selectedAddress(events.district, events.neighborhood, events.postCode)
                }
            }

            is BankingEvents.SelectedBeneficiary -> {
                viewModelScope.launch {
                    repo.selectedBeneficiary(events.name, events.lastName, events.nss)
                }
            }

            is BankingEvents.SelectedPhoneNumber -> {
                _accountState.update { state ->
                    state.copy(phoneNumber = events.phoneNumber)
                }
            }
        }
    }

    fun  initDataBase() {
        viewModelScope.launch {
            repo.insertBankingData(
                BankingEntity(
                    idDoc = 0,
                    name = "",
                    lastName = "",
                    district = "",
                    neighborhood = "",
                    postCode = 0,
                    phone = 0,
                    nss = 0,
                    valueAccount = 0L
                )
            )
        }
    }

    fun operationsAvailable() : Boolean {
        val state = _accountState.value
        return state.nss != null && state.postCode != null && state.lastName != null && state.name != null
    }

    private fun getAllBankingData() {
        viewModelScope.launch {
            repo.getAllBankingData().collect { s ->
                s?.let {
                    _accountState.value = _accountState.value.copy(
                        name = it.name,
                        lastName = it.lastName,
                        nss = it.nss,
                        district = it.district,
                        neighborhood = it.neighborhood,
                        postCode = it.postCode,
                        phoneNumber = it.phone,
                        valueAccount = it.valueAccount.toDouble()
                    )
                    Log.d("Banking Data", "name: ${it.name}, lastname: ${it.lastName}, neighborhood: ${it.neighborhood}")
                }
            }
        }
    }

    fun reformatValues() {
        _operationState.value = _operationState.value.copy(
            rawText = "",
            depositInSites = null
        )
    }

    fun formatText(): String {

        val text = _operationState.value.rawText.toDoubleOrNull()

        return if (text != null) {
            formatCurrency(text)
        } else ""
    }

    fun formatCurrency(amount: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        formatter.minimumFractionDigits = 0
        formatter.maximumFractionDigits = 0
        return formatter.format(amount)
    }

    fun transferCurrently(keyCard: Int, titular: String, institution: String) {

        val newTransfer = BankContacts(
            key = keyCard,
            titular = titular,
            institutionBank = institution,
            amountDeposit = _operationState.value.rawText.toDouble()
        )

        viewModelScope.launch {

            repo.withdraw((_accountState.value.valueAccount - _operationState.value.rawText.toInt()).toInt())

            _operationState.update { state ->
                state.copy(
                    transferHistory = state.transferHistory + newTransfer
                )
            }
        }
    }

    fun saveModeTheme(isDarkMode: Boolean) {
        _accountState.update { state ->
            state.copy(
                darkMode = isDarkMode
            )
        }
        sharedPreferencesRepo.saveModeTheme(isDarkMode)
    }

    private fun getModeTheme() {
        _accountState.update { state ->
            state.copy(
                darkMode = sharedPreferencesRepo.getModeTheme()
            )
        }
    }
}