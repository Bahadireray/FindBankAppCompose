package com.bahadireray.findbankapp.presentation.screens.list

import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity

data class BankListUiState(
    val isLoading: Boolean = false,
    val allList: List<BankInfoEntity> = arrayListOf(),
    val favoriteList: List<BankInfoEntity> = arrayListOf(),
)