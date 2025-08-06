package com.horizon.bancamovil.domain.model

data class DepositSite(
    val name: String,
    val note: String,
    val imageRes: Int,
    val maxAmount: Double,
    val cost: String = "Gratis",
    val isFree: Boolean = true
)
