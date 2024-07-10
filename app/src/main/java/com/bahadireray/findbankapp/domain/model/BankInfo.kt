package com.bahadireray.findbankapp.domain.model

data class BankInfo(
  val ID: Int,
  val dc_SEHIR: String,
  val dc_ILCE: String,
  val dc_BANKA_SUBE: String,
  val dc_BANKA_TIPI: String,
  val dc_BANK_KODU: String,
  val dc_ADRES_ADI: String,
  val dc_ADRES: String,
  val dc_POSTA_KODU: String,
  val dc_ON_OFF_LINE: String,
  val dc_ON_OFF_SITE: String,
  val dc_BOLGE_KOORDINATORLUGU: String,
  val dc_EN_YAKIM_ATM: String
)
