package com.bahadireray.findbankapp.data.remote

import com.bahadireray.findbankapp.domain.model.BankInfo
import retrofit2.http.GET

interface BankService {
  @GET("bankdata")
  suspend fun getBankList(): List<BankInfo>
}
