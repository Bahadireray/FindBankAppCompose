package com.bahadireray.findbankapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun BankDetailText(
  text: String,
  fontSize: TextUnit,
  modifier: Modifier = Modifier,
  color: Color = Color.White,
  fontWeight: FontWeight = FontWeight.Bold
) {
  Text(
    text = text,
    color = color,
    fontSize = fontSize,
    fontWeight = fontWeight,
    modifier = modifier.padding(bottom = 16.dp)
  )
}
