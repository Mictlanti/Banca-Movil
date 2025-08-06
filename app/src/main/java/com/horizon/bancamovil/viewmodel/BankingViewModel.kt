package com.horizon.bancamovil.viewmodel

import androidx.lifecycle.ViewModel
import com.horizon.bancamovil.data.state.DataUser
import com.horizon.bancamovil.data.state.DepositState
import com.horizon.bancamovil.events.BankingEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.util.Locale

class BankingViewModel : ViewModel() {

    private val _operationState = MutableStateFlow(DepositState())
    val operationState : StateFlow<DepositState> = _operationState.asStateFlow()

    private val _accountState = MutableStateFlow(DataUser())
    val accountState : StateFlow<DataUser> = _accountState.asStateFlow()

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

            }
            is BankingEvents.SelectedBeneficiary -> TODO()
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

}