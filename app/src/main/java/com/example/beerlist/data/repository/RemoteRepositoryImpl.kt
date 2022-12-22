package com.example.beerlist.data.repository

import com.example.beerlist.data.api.RemoteBeerApi
import com.example.beerlist.data.model.AllBeers
import com.example.beerlist.data.model.AllBeersItem
import com.example.beerlist.utils.Resource
import retrofit2.Response
import javax.inject.Inject


class RemoteRepositoryImpl @Inject constructor(
    private val apiService : RemoteBeerApi
) : RemoteRepository {

    override suspend fun getAllBeers(): List<AllBeersItem> {
        return apiService.getAllBeers()
    }

}