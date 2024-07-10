package com.bahadireray.findbankapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.navigation.compose.rememberNavController
import com.bahadireray.findbankapp.navigation.Navigation
import com.bahadireray.findbankapp.ui.theme.FindBankAppTheme
import com.bahadireray.findbankapp.util.AnalyticsHelper
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalTextApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private lateinit var firebaseAnalytics: FirebaseAnalytics

  @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      firebaseAnalytics = Firebase.analytics
      AnalyticsHelper.initialize(firebaseAnalytics)
      FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
      FindBankAppTheme {
        val navController = rememberNavController()
        Navigation(
          navController = navController,
        )
      }
    }
  }
}

