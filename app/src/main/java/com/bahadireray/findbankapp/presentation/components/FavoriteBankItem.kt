package com.bahadireray.findbankapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity

@Composable
fun FavoriteBankItem(
    item: BankInfoEntity,
    onItemClicked: (BankInfoEntity) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = DarkGray
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(6.dp)
            .width(86.dp)
            .clickable {
                onItemClicked(item)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    top = 4.dp,
                    bottom = 4.dp,
                    end = 12.dp
                )
        ) {
            Text(
                text = item.city,
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.bankCode,
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true, backgroundColor = 0xFF09101B)
@Composable
fun PreviewFavoriteBankItem() {
    FavoriteBankItem(
        item = BankInfoEntity(
            bankBranch = "İstanbul Banka Şubesi",
            address = "456 İstanbul Cad.",
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
        ),
        onItemClicked = {}
    )
}
