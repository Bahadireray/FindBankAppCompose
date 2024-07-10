package com.bahadireray.findbankapp.presentation.screens.detail

import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity

sealed class BankDetailUiEvent {
  data class OnLocationClicked(val bankInfo: BankInfoEntity) : BankDetailUiEvent()
  data object OnBackClicked : BankDetailUiEvent()
}