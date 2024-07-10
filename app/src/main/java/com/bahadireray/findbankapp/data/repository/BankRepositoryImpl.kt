package com.bahadireray.findbankapp.data.repository

import com.bahadireray.findbankapp.data.remote.BankService
import com.bahadireray.findbankapp.domain.model.BankInfo
import com.bahadireray.findbankapp.domain.repository.BankRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
  private val bankService: BankService
) : BankRepository {

  override suspend fun getBankList(): Flow<List<BankInfo>> = flow {
    val response = bankService.getBankList()
    emit(response)
  }.flowOn(Dispatchers.IO)
}
