package com.example.beerlist.presentation.viewModels

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerlist.data.model.AllBeers
import com.example.beerlist.data.model.AllBeersItem
import com.example.beerlist.data.repository.RemoteRepository
import com.example.beerlist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val repository: RemoteRepository
) : ViewModel() {

    var allBeers = mutableStateOf<List<AllBeersItem>>(emptyList())
    var isLoading = mutableStateOf(false)

    init {
        getAllBeers()
    }

    fun getAllBeers() {
        viewModelScope.launch {
            invoke().onEach { result ->
                delay(500L)
                when (result) {
                    is Resource.Success -> {
                        allBeers.value = result.data ?: emptyList()
                        isLoading.value = false
                    }
                    is Resource.Loading -> {
                        isLoading.value = true
                    }
                        is Resource.Error -> {
                            allBeers.value = emptyList()
                            isLoading.value = false
                        }
                }
            }.launchIn(this)
        }
    }



    fun invoke() : Flow<Resource<List<AllBeersItem>>> = flow {
        try {
            emit(Resource.Loading())
            val beers = repository.getAllBeers()
            emit(Resource.Success<List<AllBeersItem>>(beers))
            isLoading.value = false
        } catch (e:HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error in viewModel when invoking api"))
            isLoading.value = false
        } catch(e:IOException) {
            emit(Resource.Error("An unknown error occured in viewModel layer"))
            isLoading.value = false
        }
    }

}