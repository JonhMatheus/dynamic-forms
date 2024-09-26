package com.example.dynamicforms.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.dynamicforms.theme.ThemeColor
import com.example.dynamicforms.theme.White

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType,
){
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier.border(
                        width = 1.dp,
                        color = Color.Gray.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxLines = maxLines,
                colors = colors(
                    focusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = White,

                    cursorColor = ThemeColor,
                    unfocusedPlaceholderColor = White,
                    unfocusedContainerColor = White,
                    unfocusedTextColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                )
            )
            if(value.isEmpty()){
                Text(
                    text = label,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(vertical = 10.dp, horizontal = 15.dp  )
                )
            }
        }
}

