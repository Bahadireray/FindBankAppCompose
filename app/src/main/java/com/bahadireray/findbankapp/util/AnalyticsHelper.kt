package com.bahadireray.findbankapp.util
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object AnalyticsHelper {

  private lateinit var firebaseAnalytics: FirebaseAnalytics

  fun initialize(analytics: FirebaseAnalytics) {
    firebaseAnalytics = analytics
  }

  private fun logCustomEvent(eventName: String, params: Bundle) {
    firebaseAnalytics.logEvent(eventName, params)
  }

  fun logServiceReguest(event: String, endpoint: String) {
    val params = Bundle().apply {
      putString("endpoint", endpoint)
    }
    logCustomEvent(event, params)
  }

  fun logFavoriteItem(event: String, endpoint: String, status: Boolean) {
    val params = Bundle().apply {
      putString("endpoint", endpoint)
      putBoolean("status", status)
    }
    logCustomEvent(event, params)
  }
}
