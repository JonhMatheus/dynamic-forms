package com.example.dynamicforms.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dynamicforms.ui.components.CustomButton
import com.example.dynamicforms.ui.components.CustomTextField
import com.example.dynamicforms.data.model.FieldModel
import com.example.dynamicforms.theme.ThemeColor
import com.example.dynamicforms.theme.White
import com.example.dynamicforms.ui.components.RadioButtonGroup
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateFormView(
    navController: NavController
){
    var titleForm by remember { mutableStateOf("") }
    val questions = remember { mutableStateListOf<FieldModel>() }

    var selectedOption by remember { mutableStateOf("Text") }


    fun addQuestion(){
        val newField = FieldModel(
            type = "",
            label = "",
            name = "",
            required = false,
            uuid = UUID.randomUUID().toString(),
            options = listOf()
        )
        questions.add(newField)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Create Form",
                        fontSize = 22.sp,
                        fontWeight = Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = White,
                    containerColor = ThemeColor,
                )
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 130.dp, 20.dp, 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            item {
                CustomTextField(
                    value = titleForm,
                    onValueChange = { titleForm = it },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    label = "Title Form",
                    maxLines = 1,
                    keyboardType = KeyboardType.Text
                )
                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }

            //TODO: TRANSFORMAR EM CUSTOM
            itemsIndexed(questions) { index, item ->
                Text(
                    "Name",
                    color = ThemeColor,
                    fontWeight = Bold
                )
                CustomTextField(
                    value = item.name,
                    onValueChange = { newName ->
                        questions[index] = item.copy(name = newName)
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    label = "Field name",
                    maxLines = 1,
                    keyboardType = KeyboardType.Text
                )
                Text(
                    "Label",
                    color = ThemeColor,
                    fontWeight = Bold
                )
                CustomTextField(
                    value = item.label,
                    onValueChange = { newLabel ->
                        questions[index] = item.copy(label = newLabel)
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    label = "Field name",
                    maxLines = 1,
                    keyboardType = KeyboardType.Text
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Checkbox(
                        checked = item.required,
                        modifier = Modifier.padding(vertical = 8.dp),
                        onCheckedChange = { value ->
                            questions[index] = item.copy(required = value)


                        }
                    )
                    Text("Required",
                        modifier = Modifier.padding(horizontal = 5.dp))
                }

                RadioButtonGroup(
                    selectedOption = selectedOption,
                    onOptionSelected = { option ->
                        selectedOption = option
                    })

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 15.dp)
                )

            }

            item {
                CustomButton(
                    onClick = { addQuestion() },
                    icon = Icons.Filled.Add,
                    text = "add field",
                    modifier = Modifier.width(140.dp).padding(vertical = 20.dp),
                    radius = 8,
                    height = 1
                )
            }

            item {
                CustomButton(
                    onClick = {
                        //TODO: FAZER CRIACAO FORM
                    },
                    text = "Create",
                    modifier = Modifier.fillMaxSize(),
                    radius = 8,
                    height = 10
                )
            }
        }
    }
    }