package com.bahadireray.findbankapp.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bahadireray.findbankapp.data.local.entity.BankInfoEntity
import com.bahadireray.findbankapp.presentation.components.BankDetailText
import com.bahadireray.findbankapp.util.UiEvent
import kotlinx.coroutines.flow.Flow

@Composable
fun BankDetailScreen(
  uiEvent: Flow<UiEvent>,
  onEvent: (BankDetailUiEvent) -> Unit,
  viewModel: BankDetailViewModel,
  bankInfo: BankInfoEntity?,
  onUiEvent: (UiEvent) -> Unit,
  ) {

  LaunchedEffect(key1 = true) {
    uiEvent.collect { event ->
      onUiEvent(event)
    }
  }

  Column(modifier = Modifier.background(Color.Black)){
    Icon(
      imageVector = Icons.Default.ArrowBack,
      contentDescription = "Cancel Icon",
      tint = Color.White,
      modifier = Modifier
        .padding(top = 64.dp, start = 32.dp)
        .clickable {
          onEvent(BankDetailUiEvent.OnBackClicked)
        }
    )

    Column(
      modifier = Modifier
        .fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      Card(
        modifier = Modifier.padding(16.dp),
        backgroundColor = Color.DarkGray,
        elevation = 8.dp
      ) {

        Column(
          modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          bankInfo?.let { info ->
            BankDetailText(
              text = info.bankBranch,
              fontSize = 24.sp
            )
            BankDetailText(
              text = info.address,
              fontSize = 18.sp
            )

            BankDetailText(
              text = info.bankType,
              fontSize = 16.sp
            )

            Button(
              onClick = {
                viewModel.onEvent(BankDetailUiEvent.OnLocationClicked(info))
              },
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(text = "Banka Konumuna Git")
            }
          }
        }
      }
    }
  }
}
