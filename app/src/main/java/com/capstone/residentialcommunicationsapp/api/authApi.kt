package com.capstone.residentialcommunicationsapp.api

import com.capstone.residentialcommunicationsapp.datamodels.AuthApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiForAuthentication{

    //OkhttpClient for building http request url
    private val authClient = OkHttpClient().newBuilder()
        .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(authClient)
        .baseUrl("https://com.capstone.residentialcommunicationsapp.api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val authApi : AuthApi = retrofit().create(AuthApi::class.java)

}