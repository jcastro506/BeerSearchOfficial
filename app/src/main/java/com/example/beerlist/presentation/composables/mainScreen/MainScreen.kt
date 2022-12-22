package com.example.beerlist.presentation.composables.mainScreen

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.beerlist.presentation.viewModels.MainScreenViewModel
import hilt_aggregated_deps._com_example_beerlist_di_module_RepoModule


@Composable
fun MainScreen(
    navController : NavController,
    viewModel: MainScreenViewModel
) {

    val beers = viewModel.allBeers
    val isLoading = viewModel.isLoading

    //if(isLoading.value) CircularProgressIndicator()
    LazyColumn() {
        items(beers.value) { beer ->
            BeerCard(beerName = beer.name, beerImage = beer.imageUrl,
                beerDescription = beer.description, isLoading = isLoading)
        }
    }
}

@Composable
fun BeerCard(
    beerName : String,
    beerImage: String,
    beerDescription: String,
    isLoading : MutableState<Boolean>
) {
    Row() {
        BeerImage(beerImage = beerImage, beerDescription = beerDescription, isLoading = isLoading)
        Column() {
            BeerName(beerName = beerName)
        }
    }
}

@Composable
fun BeerImage(
    beerImage : String,
    beerDescription: String,
    isLoading: MutableState<Boolean>
) {
    AsyncImage(model = if(isLoading.value) CircularProgressIndicator() else beerImage,
        contentDescription = beerDescription)
}

@Composable
fun BeerName (
    beerName : String
) {
    Text(text = beerName)
}