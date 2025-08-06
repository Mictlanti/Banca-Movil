package com.horizon.bancamovil.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//Flow recommended to integrated room + dagger - hilt
//1 - Created the entitys from the project

@Entity(tableName = "banking_table")
data class BankingEntity(
    @PrimaryKey val idDoc : Int = 0,
    val name: String,
    val lastName: String,
    val district: String,
    val neighborhood: String,
    val postCode: Int,
    val phone: Int,
    val nss: Int,
    val valueAccount: Long
)
