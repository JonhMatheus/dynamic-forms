package com.example.dynamicforms.ui.components

import android.annotation.SuppressLint
import android.text.Html
import android.text.Spanned
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicforms.data.model.FieldModel
import com.example.dynamicforms.theme.ThemeColor

@SuppressLint("NewApi")
@Composable
fun SelectedField(
    field: FieldModel,
    response: SnapshotStateMap<String, String>
) {

    val currentText = response[field.name] ?: ""

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 25.dp)
    ) {
        Text(
            if (field.type != "description") field.label else field.name,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = ThemeColor,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        when (field.type) {
            "dropdown" -> DropDownForm(
                field = field,
                selectedOption = currentText,
                onValueChanged = { value ->
                    response[field.name] = value
                }
            )
            "description" -> Text(fromHtml(field.label).toString())
            "number" -> CustomTextField(
                value = currentText,
                onValueChange = { value ->
                    response[field.name] = value
                },
                modifier = Modifier.fillMaxWidth(),
                label = field.name,
                maxLines = 1,
                keyboardType = KeyboardType.Number,
            )
            else -> CustomTextField(
                value = currentText,
                onValueChange = { value ->
                    response[field.name] = value
                },
                modifier = Modifier.fillMaxWidth(),
                label = field.name,
                maxLines = 1,
                keyboardType = KeyboardType.Text,
            )
        }
    }
}

@SuppressLint("NewApi")
fun fromHtml(html: String): Spanned {
    return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)

}
