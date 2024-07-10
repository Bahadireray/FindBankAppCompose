package com.bahadireray.findbankapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity

@Composable
fun BankListItem(
  item: BankInfoEntity,
  onItemClicked: (BankInfoEntity) -> Unit,
  onFavoriteClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .background(Color.Black)
      .padding(12.dp)
      .clickable { onItemClicked(item) }
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = "${item.city}, ${item.district}",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.weight(1.2f)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "Kod: ${item.bankCode}",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.weight(1f)
      )
      Icon(
        imageVector = Icons.Default.Star,
        tint = if (item.isFavorite == true) Color.Yellow else Color.Gray,
        contentDescription = "Favorite Icon",
        modifier = Modifier.clickable { onFavoriteClick() }
      )
    }
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = "${item.address}",
      color = Color.White,
      style = MaterialTheme.typography.bodyLarge
    )
  }
}


@Preview(showBackground = true)
@Composable
fun BankListItemPreview() {
  val sampleItem = BankInfoEntity(
    bankBranch = "İstanbul Banka Şubesi",
    address = "456 Örnek Cad.",
    city = "İstanbul Şehir",
    isFavorite = true,
    district = "İstanbul İlçe",
    addressName = "İstanbul Adres Adı",
    bankCode = "1234",
    bankType = "İstanbul Banka Türü",
    nearestAtm = "En Yakın ATM",
    onlineStatus = "Çevrimiçi Durumu",
    onSiteStatus = "Mevcut Durumu",
    postalCode = "54321",
    regionalCoordination = "Bölgesel Koordinasyon"
  )

  BankListItem(
    item = sampleItem,
    onItemClicked = {  },
    onFavoriteClick = {  }
  )
}