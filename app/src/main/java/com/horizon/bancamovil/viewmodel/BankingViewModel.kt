package com.horizon.bancamovil.viewmodel

import androidx.lifecycle.ViewModel
import com.horizon.bancamovil.data.model.BankContacts
import com.horizon.bancamovil.data.state.DataUser
import com.horizon.bancamovil.data.state.DepositState
import com.horizon.bancamovil.events.BankingEvents
import com.horizon.bancamovil.repo.BankingRepo
import com.horizon.bancamovil.repo.PreferencesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BankingViewModel @Inject constructor(
    private val repo: BankingRepo,
    private val sharedPreferencesRepo: PreferencesRepo
) : ViewModel() {

    private val _operationState = MutableStateFlow(DepositState())
    val operationState : StateFlow<DepositState> = _operationState.asStateFlow()

    private val _accountState = MutableStateFlow(DataUser())
    val accountState : StateFlow<DataUser> = _accountState.asStateFlow()

    init {
        getModeTheme()
    }

    fun events(events: BankingEvents) {
        when(events) {
            is BankingEvents.OnValueChange -> {
                val cleanedText = events.value.filter { it.isDigit() }

                _operationState.value = _operationState.value.copy(
                    rawText = cleanedText
                )
            }
            is BankingEvents.DepositInEvent -> {

                val valueAccount = _accountState.value.valueAccount

                _accountState.update { state ->
                    state.copy(
                        valueAccount = valueAccount + events.value
                    )
                }
            }
            is BankingEvents.AvailableSitesDeposit -> {
                _operationState.value = _operationState.value.copy(
                    depositInSites = events.index
                )
            }
            is BankingEvents.WithdrawEvent -> {
                val valueAccount = _accountState.value.valueAccount

                _accountState.update { state ->
                    state.copy(
                        valueAccount = valueAccount - events.amount
                    )
                }
            }
            is BankingEvents.SelectedAddress -> {
                _accountState.update { state ->
                    state.copy(
                        district = events.district,
                        neighborhood = events.neighborhood,
                        postCode = events.postCode
                    )
                }
            }
            is BankingEvents.SelectedBeneficiary -> {
                _accountState.update { state ->
                    state.copy(
                        name = events.name,
                        lastName = events.lastName,
                        nss = events.nss
                    )
                }
            }
            is BankingEvents.SelectedPhoneNumber -> {
                _accountState.update { state ->
                    state.copy(phoneNumber = events.phoneNumber)
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

    fun formatText() : String {

        val text = _operationState.value.rawText.toDoubleOrNull()

        return if(text != null) {
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

        _operationState.update { state ->
            state.copy(
                transferHistory = state.transferHistory + newTransfer
            )
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