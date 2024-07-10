package com.bahadireray.findbankapp.presentation.screens.detail

import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity

data class BankDetailUiState(
  val isLoading: Boolean = false,
  val bankInfo: BankInfoEntity? = null
)