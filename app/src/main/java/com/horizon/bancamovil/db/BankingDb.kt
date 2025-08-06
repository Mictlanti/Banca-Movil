package com.horizon.bancamovil.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.horizon.bancamovil.dao.BankingDao
import com.horizon.bancamovil.entitys.BankingEntity

//3 - Defines the database and linked the dao

@Database(entities = [BankingEntity::class], version = 1)
abstract class BankingDb : RoomDatabase() {

    abstract fun bankingDao() : BankingDao

}