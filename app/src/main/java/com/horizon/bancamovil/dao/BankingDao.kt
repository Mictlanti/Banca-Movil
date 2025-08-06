package com.horizon.bancamovil.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.horizon.bancamovil.entitys.BankingEntity
import kotlinx.coroutines.flow.Flow

//2 - Defines the operations to the db (consults, insertions, updates, deletes, ...)

@Dao
interface BankingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanking(bankingEntity: BankingEntity)

    @Query("SELECT * FROM banking_table")
    fun getAllBankingData() : Flow<List<BankingEntity>>

    @Delete
    suspend fun deleteBankingData(bankingEntity: BankingEntity)

}