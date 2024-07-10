package com.bahadireray.findbankapp.presentation.screens.list

import androidx.lifecycle.viewModelScope
import com.bahadireray.findbankapp.base.BaseViewModel
import com.bahadireray.findbankapp.common.ResultState
import com.bahadireray.findbankapp.data.local.BankDataRepository
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity
import com.bahadireray.findbankapp.domain.use_case.GetBankListUseCase
import com.bahadireray.findbankapp.navigation.NavigationType
import com.bahadireray.findbankapp.navigation.Route
import com.bahadireray.findbankapp.util.ExceptionHandler
import com.bahadireray.findbankapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BankListViewModel @Inject constructor(
  private val getBankListUseCase: GetBankListUseCase,
  private val bankDataRepository: BankDataRepository
) : BaseViewModel() {

  private val _uiState = MutableStateFlow(BankListUiState())
  val uiState: StateFlow<BankListUiState> = _uiState.asStateFlow()

  init {
    fetchBankList()
  }

  private fun fetchBankList() {
    viewModelScope.launch(ExceptionHandler.handler) {
      updatePageLoading(true)
      getBankListUseCase { result ->
        when (result) {
          is ResultState.Success -> {
            viewModelScope.launch {
              val uniqueData = result.data.distinctBy { it.id }

              val existingList = bankDataRepository.allList().firstOrNull()

              val updatedData = uniqueData.map { newItem ->
                val existingItem = existingList?.find { it.id == newItem.id }
                newItem.copy(isFavorite = existingItem?.isFavorite ?: false)
              }.distinctBy { it.id }
              bankDataRepository.addAll(updatedData)
              _uiState.update { currentState ->
                currentState.copy(allList = updatedData, isLoading = false)
              }
            }
          }
          is ResultState.Error -> {
            handleException(result.exception)
            updatePageLoading(false)
          }
          ResultState.Complete -> TODO()
        }
      }
    }
  }

  fun onEvent(event: BankListUiEvent) {
    when (event) {
      is BankListUiEvent.OnBankItemClicked -> {
        onBankItemClick(event.item)
      }
      is BankListUiEvent.OnFavoriteClicked -> {
        updateFavoriteBank(event.item)
      }
    }
  }

  private fun onBankItemClick(bankInfo: BankInfoEntity) {
    viewModelScope.launch(ExceptionHandler.handler) {
      sendUiEvent(
        UiEvent.Navigate(
          navigationType = NavigationType.Navigate(Route.BankDetailScreen),
          data = mapOf(
            "bankInfo" to bankInfo
          )
        )
      )
    }
  }

  private fun updateFavoriteBank(item: BankInfoEntity) {
    viewModelScope.launch {
      val updatedItem = withContext(Dispatchers.IO) {
        val updated = item.copy(isFavorite = !item.isFavorite!!)
        bankDataRepository.updateBankInfo(updated)
        updated
      }

      _uiState.update { currentState ->
        val updatedList = currentState.allList.map { if (it.bankBranch == updatedItem.bankBranch) updatedItem else it }
        currentState.copy(allList = updatedList)
      }
    }
  }

  private fun updatePageLoading(isLoading: Boolean) {
    viewModelScope.launch(ExceptionHandler.handler) {
      _uiState.update { currentState ->
        currentState.copy(isLoading = isLoading)
      }
    }
  }
}

