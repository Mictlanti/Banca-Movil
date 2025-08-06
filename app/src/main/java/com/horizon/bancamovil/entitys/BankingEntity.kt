package com.horizon.bancamovil.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

//Flow recommended to integrated room + dagger - hilt
//1 - Created the entitys from the project

@Entity(tableName = "banking_table")
data class BankingEntity(
    @PrimaryKey(autoGenerate = true) val idDoc : Int = 0,
    val name: String,
    val direction: String,
    val postCode: Int,
    val phone: Int,
    val nss: Int,
    val valueAccount: Int
)
