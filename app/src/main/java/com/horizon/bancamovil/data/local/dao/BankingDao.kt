package com.horizon.bancamovil.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.horizon.bancamovil.data.local.entities.BankingEntity
import kotlinx.coroutines.flow.Flow

//2 - Defines the operations to the db (consults, insertions, updates, deletes, ...)

@Dao
interface BankingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanking(bankingEntity: BankingEntity)

    @Query("SELECT * FROM banking_table LIMIT 1")
    fun getAllBankingData(): Flow<BankingEntity?>

    @Query("UPDATE banking_table SET valueAccount = :newValue WHERE idDoc = 0")
    suspend fun deposit(newValue: Int)

    @Query("UPDATE banking_table SET valueAccount = :newValue WHERE idDoc = 0")
    suspend fun transfer(newValue: Int)

    @Query("UPDATE banking_table SET name = :name, lastName = :lastName, nss = :nss WHERE idDoc = 0")
    suspend fun selectedBeneficiary(name: String, lastName: String, nss: Int)

    @Query(
        """
    UPDATE banking_table 
    SET district = :direction, neighborhood = :neighborhood, postCode = :postCode 
    WHERE idDoc = 0
        """
    )
    suspend fun selectedAddress(direction: String, neighborhood: String, postCode: Int)

    @Delete
    suspend

    fun deleteBankingData(bankingEntity: BankingEntity)

}