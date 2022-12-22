package com.example.beerlist.data.repository

import com.example.beerlist.data.model.AllBeers
import com.example.beerlist.data.model.AllBeersItem
import com.example.beerlist.utils.Resource
import retrofit2.Response

interface RemoteRepository {

    suspend fun getAllBeers() : List<AllBeersItem>

}