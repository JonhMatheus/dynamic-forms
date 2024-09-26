package com.example.dynamicforms.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dynamicforms.data.model.FormResponse
import com.example.dynamicforms.data.model.ResponseModel
import com.example.dynamicforms.ui.components.SelectedField
import com.example.dynamicforms.theme.ThemeColor
import com.example.dynamicforms.theme.White
import com.example.dynamicforms.ui.components.CustomButton
import com.example.dynamicforms.viewModels.FormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponseFormView(
    navController: NavController,
    viewModel: FormViewModel
) {
    val responses = remember { mutableStateMapOf<String, String>() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        viewModel.formModel?.title ?: "Loading...",
                        fontSize = 22.sp,
                        fontWeight = Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = White,
                    containerColor = ThemeColor,
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (viewModel.formModel?.fields != null) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    items(viewModel.formModel!!.fields) { item ->
                        SelectedField(item, responses)
                    }
                }
                //TODO: FAZER CIRCULAR PROGRESS INDICATOR COM ENUM STATUS
                CustomButton(
                    onClick = {
                        val formResponse = FormResponse(
                            title = viewModel.formModel?.title ?: "",
                            responses = responses.map { ResponseModel(it.key, it.value) }
                        )
                        viewModel.postFormResponse(formResponse)
                        navController.popBackStack()

                    },
                    text = "Submit Responses",
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    radius = 8,
                    height = 10
                )

            }
        }
    }
}