package com.bahadireray.findbankapp.domain.repository

import com.bahadireray.findbankapp.domain.model.BankInfo
import kotlinx.coroutines.flow.Flow

interface BankRepository {
  suspend fun getBankList(): Flow<List<BankInfo>>
}
