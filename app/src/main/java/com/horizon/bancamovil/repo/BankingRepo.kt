package com.horizon.bancamovil.repo

import com.horizon.bancamovil.dao.BankingDao
import com.horizon.bancamovil.entitys.BankingEntity
import javax.inject.Inject

//5 - The repository is an intermediate layer between the DAO and your ViewModel, to keep the data logic separate.

class BankingRepo @Inject constructor(
    private val bankingDao: BankingDao
) {

    fun getAllBankingData() = bankingDao.getAllBankingData()

    suspend fun insertBankingData(bankingEntity: BankingEntity) {
        bankingDao.insertBanking(bankingEntity)
    }

    suspend fun deleteBankingData(bankingEntity: BankingEntity) {
        bankingDao.deleteBankingData(bankingEntity)
    }



}