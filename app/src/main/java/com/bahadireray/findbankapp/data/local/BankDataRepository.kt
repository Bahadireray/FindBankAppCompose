package com.bahadireray.findbankapp.data.local

import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BankDataRepository @Inject constructor(private val bankInfoDao: BankInfoDao) {

  fun allList(): Flow<List<BankInfoEntity>> {
    return bankInfoDao.readAllBankInfoData()
  }

  suspend fun addAll(bankInfo: List<BankInfoEntity>) {
    bankInfoDao.addAllBankInfo(bankInfo)
  }

  suspend fun updateBankInfo(bankInfo: BankInfoEntity) {
    bankInfoDao.updateBankInfo(bankInfo)
  }

  suspend fun deleteAllBankInfo() {
    bankInfoDao.deleteAllBankInfo()
  }
}
