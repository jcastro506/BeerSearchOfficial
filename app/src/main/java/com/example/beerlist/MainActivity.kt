package com.example.beerlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beerlist.presentation.composables.mainScreen.MainScreen
import com.example.beerlist.presentation.viewModels.MainScreenViewModel
import com.example.beerlist.ui.theme.BeerListTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeerListTheme {
                val navController = rememberNavController()
                val mainScreenViewModel : MainScreenViewModel by viewModels()
                NavHost(navController = navController, startDestination = "main_screen" ) {
                    composable("main_screen") {
                        MainScreen(navController=navController, viewModel =mainScreenViewModel)
                    }
                }
            }
        }
    }
}