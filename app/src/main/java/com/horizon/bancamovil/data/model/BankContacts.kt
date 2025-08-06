package com.horizon.bancamovil.data.model

data class BankContacts(
    val titular : String,
    val key : Int,
    val institutionBank : String,
    val amountDeposit: Double
)