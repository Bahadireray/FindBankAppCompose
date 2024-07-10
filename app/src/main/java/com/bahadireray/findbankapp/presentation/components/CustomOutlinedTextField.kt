package com.bahadireray.findbankapp.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextField(
  value: String,
  onValueChange: (String) -> Unit,
  placeholder: String,
  modifier: Modifier = Modifier,
  textStyle: TextStyle = TextStyle(color = Color.White),
  singleLine: Boolean = true,
  colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
  shape: Shape = RoundedCornerShape(8.dp)
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier.height(56.dp),
    textStyle = textStyle,
    placeholder = { Text(text = placeholder, color = Color.Gray) },
    singleLine = singleLine,
    shape = shape,
    colors = colors
  )
}
