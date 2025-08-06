package com.horizon.bancamovil.domain.state

import com.horizon.bancamovil.domain.model.BankContacts

data class DepositState(
    val rawText: String = "",
    val depositInSites : Int? = null,
    val transferHistory : List<BankContacts> = emptyList(),
    val favoritesContacts : List<BankContacts> = emptyList()
)
