package com.bahadireray.findbankapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "bank_info")
data class BankInfoEntity(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  val id: Int = 0,
  @ColumnInfo(name = "dc_SEHIR")
  val city: String,
  @ColumnInfo(name = "dc_ILCE")
  val district: String,
  @ColumnInfo(name = "dc_BANKA_SUBE")
  val bankBranch: String,
  @ColumnInfo(name = "dc_BANKA_TIPI")
  val bankType: String,
  @ColumnInfo(name = "dc_BANK_KODU")
  val bankCode: String,
  @ColumnInfo(name = "dc_ADRES_ADI")
  val addressName: String,
  @ColumnInfo(name = "dc_ADRES")
  val address: String,
  @ColumnInfo(name = "dc_POSTA_KODU")
  val postalCode: String,
  @ColumnInfo(name = "dc_ON_OFF_LINE")
  val onlineStatus: String,
  @ColumnInfo(name = "dc_ON_OFF_SITE")
  val onSiteStatus: String,
  @ColumnInfo(name = "dc_BOLGE_KOORDINATORLUGU")
  val regionalCoordination: String,
  @ColumnInfo(name = "dc_EN_YAKIM_ATM")
  val nearestAtm: String,
  @ColumnInfo(name = "is_favorite")
  var isFavorite: Boolean? = null
) : Parcelable

