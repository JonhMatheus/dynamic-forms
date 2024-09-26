package com.example.dynamicforms.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dynamicforms.di.appModule
import com.example.dynamicforms.theme.DynamicFormsTheme
import com.example.dynamicforms.view.CreateFormView
import com.example.dynamicforms.view.Home
import com.example.dynamicforms.view.ResponseFormView
import com.example.dynamicforms.viewModels.FormViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(appModule)
        }
        enableEdgeToEdge()
        setContent {
            DynamicFormsTheme {

                val navController = rememberNavController()
                val viewModel: FormViewModel = getViewModel()

                NavHost(navController = navController, startDestination = "Home"){
                    composable("Home") { Home(navController, viewModel) }
                    composable("ResponseFormView") { ResponseFormView(navController, viewModel) }
                    composable("CreateFormView") { CreateFormView(navController) }
                }
            }
        }
    }
}
