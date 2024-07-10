package com.bahadireray.findbankapp.util

import com.bahadireray.findbankapp.navigation.NavigationType

sealed class UiEvent {
  data class Navigate<T>(val navigationType: NavigationType, val data: Map<String, T?>? = null) : UiEvent()
  data class ShowError(val throwable: Throwable?) : UiEvent()
}