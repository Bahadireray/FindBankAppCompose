package com.bahadireray.findbankapp.presentation.screens.list

import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity

sealed class BankListUiEvent {
    data class OnBankItemClicked(val item: BankInfoEntity) : BankListUiEvent()
    data class OnFavoriteClicked(val item: BankInfoEntity) : BankListUiEvent()
}