package com.bahadireray.findbankapp.domain.use_case

import com.bahadireray.findbankapp.common.ResultState
import com.bahadireray.findbankapp.data.local.BankDataRepository
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity
import com.bahadireray.findbankapp.domain.model.BankInfo
import com.bahadireray.findbankapp.domain.repository.BankRepository
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class GetBankListUseCase @Inject constructor(
  private val bankRepository: BankRepository,
  private val bankDataRepository: BankDataRepository
) {
  suspend operator fun invoke(onResult: (ResultState<List<BankInfoEntity>>) -> Unit) {
    bankRepository.getBankList()
      .catch { exception ->
        onResult(ResultState.Error(exception))
      }
      .collect { bankList ->
        val newList = convertResponseToUI(bankList)
        bankDataRepository.addAll(newList)
        onResult(ResultState.Success(newList))
      }
  }

  private fun convertResponseToUI(response: List<BankInfo>): List<BankInfoEntity> {
    return response.map { item ->
      with(item) {
        BankInfoEntity(
          id = ID,
          city = dc_SEHIR,
          district = dc_ILCE,
          bankBranch = dc_BANKA_SUBE,
          bankType = dc_BANKA_TIPI,
          bankCode = dc_BANK_KODU,
          addressName = dc_ADRES_ADI,
          address = dc_ADRES,
          postalCode = dc_POSTA_KODU,
          onlineStatus = dc_ON_OFF_LINE,
          onSiteStatus = dc_ON_OFF_SITE,
          regionalCoordination = dc_BOLGE_KOORDINATORLUGU,
          nearestAtm = dc_EN_YAKIM_ATM
        )
      }
    }
  }
}
