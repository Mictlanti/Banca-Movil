package com.horizon.bancamovil.events

sealed class BankingEvents {

    data class OnValueChange(val value: String) : BankingEvents()

    data class DepositInEvent(val value: Double) : BankingEvents()

    data class AvailableSitesDeposit(val index: Int?) : BankingEvents()

    data class WithdrawEvent(val amount: Double) : BankingEvents()

    data class SelectedBeneficiary(val username: String) : BankingEvents()

    data class SelectedAddress(val address : String) : BankingEvents()

}