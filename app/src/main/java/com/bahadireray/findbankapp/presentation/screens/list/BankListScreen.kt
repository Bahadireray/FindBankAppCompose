package com.bahadireray.findbankapp.presentation.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahadireray.findbankapp.presentation.components.BankListItem
import com.bahadireray.findbankapp.presentation.components.CustomOutlinedTextField
import com.bahadireray.findbankapp.presentation.components.FavoriteBankItem
import com.bahadireray.findbankapp.util.AnalyticsHelper
import com.bahadireray.findbankapp.util.UiEvent
import kotlinx.coroutines.flow.Flow

@Composable
fun BankListScreen(
  uiState: BankListUiState,
  uiEvent: Flow<UiEvent>,
  onEvent: (BankListUiEvent) -> Unit,
  onUiEvent: (UiEvent) -> Unit,
) {
  var searchQuery by remember { mutableStateOf("") }

  LaunchedEffect(key1 = true) {
    uiEvent.collect { event ->
      onUiEvent(event)
    }
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black),
    contentAlignment = Alignment.Center
  ) {
    if (uiState.isLoading) {
      CircularProgressIndicator(color = Color.White)
    }
    Column(modifier = Modifier.padding(top = 32.dp)) {
      Text(
        modifier = Modifier.padding(horizontal = 12.dp),
        text = "Favorite Banks",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge
      )
      LazyRow(
        modifier = Modifier
          .padding(horizontal = 12.dp)
          .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        items(uiState.allList.filter { it.isFavorite == true }) { item ->
          FavoriteBankItem(
            item = item,
            onItemClicked = {
              onEvent(
                BankListUiEvent.OnBankItemClicked(
                  item = item
                )
              )
            },
          )
        }
      }

      CustomOutlinedTextField(
        value = searchQuery,
        onValueChange = { query ->
          searchQuery = query
        },
        placeholder = "Search by City",
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp, bottom = 4.dp)
      )

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
      ) {
        item {
          Spacer(modifier = Modifier.height(12.dp))
        }
        if (uiState.allList.isNotEmpty()) {
          item {
            Text(
              modifier = Modifier.padding(horizontal = 12.dp),
              text = "All Banks",
              color = Color.White,
              fontWeight = FontWeight.Bold,
              style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
          }

          items(uiState.allList.filter {
            it.city.contains(searchQuery, ignoreCase = true)
          }) { item ->
            BankListItem(
              item = item,
              onItemClicked = {
                AnalyticsHelper.logServiceReguest ("Bank İtem","${item.bankCode}")
                onEvent(BankListUiEvent.OnBankItemClicked(item))
              },
              onFavoriteClick = {
                AnalyticsHelper.logFavoriteItem ("Bank İtem","${item.bankCode}",true)
                onEvent(BankListUiEvent.OnFavoriteClicked(item))
              }
            )
            Divider(
              modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = Color.White)
            )
          }
        }
      }
    }
  }
}
