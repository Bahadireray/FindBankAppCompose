package com.bahadireray.findbankapp.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class FindBankApp : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
  }
}