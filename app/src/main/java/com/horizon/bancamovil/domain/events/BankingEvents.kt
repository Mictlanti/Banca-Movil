package com.horizon.bancamovil.domain.events

sealed class BankingEvents {

    data class OnValueChange(val value: String) : BankingEvents()

    data class DepositInEvent(val value: Double) : BankingEvents()

    data class AvailableSitesDeposit(val index: Int?) : BankingEvents()

    data class WithdrawEvent(val amount: Double) : BankingEvents()

    data class SelectedBeneficiary(val name: String, val lastName: String, val nss : Int) : BankingEvents()

    data class SelectedAddress(val district : String, val neighborhood: String, val postCode: Int) : BankingEvents()

    data class SelectedPhoneNumber(val phoneNumber: Int) : BankingEvents()

}