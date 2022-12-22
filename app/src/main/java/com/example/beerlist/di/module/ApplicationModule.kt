package com.example.beerlist.di.module

import com.example.beerlist.data.api.RemoteBeerApi
import com.example.beerlist.data.repository.RemoteRepository
import com.example.beerlist.data.repository.RemoteRepositoryImpl
import com.example.beerlist.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideBeerApi() : RemoteBeerApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteBeerApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteRepo(api: RemoteBeerApi) : RemoteRepository {
        return RemoteRepositoryImpl(api)
    }
}