package com.horizon.bancamovil.data.model

data class DepositSite(
    val name: String,
    val note: String,
    val imageRes: Int,
    val maxAmount: Double,
    val cost: String = "Gratis",
    val isFree: Boolean = true
)
