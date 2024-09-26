package com.example.dynamicforms.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicforms.theme.ThemeColor
import com.example.dynamicforms.theme.White

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier,
    icon: ImageVector? = null,
    radius: Int,
    height: Int,
){
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(radius.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ThemeColor
        ),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Start
        ) {
            if(icon != null){
                Icon(
                    imageVector = icon,
                    tint = White,
                    contentDescription = "Add Icon"
                )
            }
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(all = height.dp)
            )
        }

    }
}