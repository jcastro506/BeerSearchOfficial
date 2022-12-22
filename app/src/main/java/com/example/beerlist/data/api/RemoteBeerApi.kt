package com.example.beerlist.data.api

import com.example.beerlist.data.model.AllBeers
import com.example.beerlist.data.model.AllBeersItem
import com.example.beerlist.utils.Resource
import retrofit2.Response
import retrofit2.http.GET

interface RemoteBeerApi {

    @GET("beers")
    suspend fun getAllBeers() : List<AllBeersItem>

}