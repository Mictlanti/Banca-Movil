package com.horizon.bancamovil.domain.state

data class DataUser(
    val name: String? = null,
    val lastName: String? = null,
    val district: String? = null,
    val neighborhood : String? = null,
    val postCode : Int? = null,
    val phoneNumber: Int? = null,
    val nss: Int ? = null,
    val valueAccount : Double = 31000000.0,
    val darkMode: Boolean = true
)
