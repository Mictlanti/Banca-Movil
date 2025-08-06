package com.horizon.bancamovil.data.repository

import com.horizon.bancamovil.data.local.dao.BankingDao
import com.horizon.bancamovil.data.local.entities.BankingEntity
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

    suspend fun deposit(amount: Int) = bankingDao.deposit(amount)

    suspend fun withdraw(amount: Int) = bankingDao.transfer(amount)

    suspend fun selectedBeneficiary(name: String, lastName: String, nss: Int) {
        bankingDao.selectedBeneficiary(name, lastName, nss)
    }

    suspend fun selectedAddress(direction: String, neighborhood: String, postCode: Int) {
        bankingDao.selectedAddress(direction, neighborhood, postCode)
    }

}