package com.example.dynamicforms.view
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dynamicforms.ui.components.FormItem
import com.example.dynamicforms.theme.ThemeColor
import com.example.dynamicforms.theme.White
import com.example.dynamicforms.viewModels.FormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    viewModel: FormViewModel

){
    val formsState by viewModel.forms.collectAsState(initial = emptyMap())

    LaunchedEffect(Unit) {
        viewModel.getForms()
    }

    //TODO: SEPARAR POR HEADER, CONTENT...
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "My Forms",
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    navController.navigate("CreateFormView")
                },
                containerColor = ThemeColor,

                ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = White,
                    contentDescription = "Add Icon"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(formsState.entries.toList()) { (key, form) ->
                FormItem(
                    form = form,
                    onTap = { offset ->
                        viewModel.formModel = form
                       navController.navigate("ResponseFormView")

                    })
            }
        }
    }
}