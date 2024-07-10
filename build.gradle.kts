buildscript {
  repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
  }
  dependencies {
    classpath ("com.android.tools.build:gradle:8.0.2")
    classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    classpath ("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
    classpath ("com.google.gms:google-services:4.3.10")
    classpath ("com.google.firebase:firebase-crashlytics-gradle:2.8.1")
  }
}

plugins {
  // Add other plugins here
  id("com.android.application") version "8.0.2" apply false
  id("org.jetbrains.kotlin.android") version "1.8.10" apply false
  id("org.jetbrains.kotlin.kapt") version "1.8.10" apply false
  id("com.android.library") version "8.0.2" apply false
  id ("com.google.dagger.hilt.android") version "2.46.1" apply false
  id("com.google.gms.google-services") version "4.4.0" apply false
}