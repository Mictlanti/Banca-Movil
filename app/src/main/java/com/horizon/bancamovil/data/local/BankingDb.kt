package com.horizon.bancamovil.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.horizon.bancamovil.data.local.dao.BankingDao
import com.horizon.bancamovil.data.local.entities.BankingEntity

//3 - Defines the database and linked the dao

@Database(entities = [BankingEntity::class], version = 3)
abstract class BankingDb : RoomDatabase() {

    abstract fun bankingDao() : BankingDao

}