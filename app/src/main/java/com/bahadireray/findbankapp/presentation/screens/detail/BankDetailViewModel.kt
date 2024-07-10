package com.bahadireray.findbankapp.presentation.screens.detail

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity
import com.bahadireray.findbankapp.navigation.NavigationType
import com.bahadireray.findbankapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankDetailViewModel @Inject constructor(
  application: Application
) : AndroidViewModel(application) {

  private val _uiState = MutableStateFlow(BankDetailUiState())
  val uiState: StateFlow<BankDetailUiState> = _uiState.asStateFlow()

  fun onEvent(event: BankDetailUiEvent) {
    when (event) {
      is BankDetailUiEvent.OnLocationClicked -> {
        openMapForBankLocation(event.bankInfo)
      }
      BankDetailUiEvent.OnBackClicked -> {
        onBackClick()
      }
    }
  }

  private fun openMapForBankLocation(bankInfo: BankInfoEntity) {
    val gmmIntentUri = Uri.parse("geo:0,0?q=${bankInfo.address}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    mapIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    getApplication<Application>().startActivity(mapIntent)
  }

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

   private fun sendUiEvent(event: UiEvent) {
    viewModelScope.launch {
      _uiEvent.send(event)
    }
  }

  private fun onBackClick() {
    sendNavigationEvent(NavigationType.PopBack)
  }

  private fun sendNavigationEvent(navigationType: NavigationType, data: Map<String, Any?>? = emptyMap()) {
    viewModelScope.launch {
      sendUiEvent(UiEvent.Navigate(navigationType, data))
    }
  }
}
