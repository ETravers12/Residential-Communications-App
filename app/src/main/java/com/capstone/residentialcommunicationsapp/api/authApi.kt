package com.capstone.residentialcommunicationsapp.api

import com.capstone.residentialcommunicationsapp.datamodels.AuthApi
import com.capstone.residentialcommunicationsapp.datamodels.IssueApi
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManagerApi
import com.capstone.residentialcommunicationsapp.datamodels.TenantApi
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
        // here, you'll need to use the local IP address of your machine
        // because when your app is running on android, "localhost"
        // refers to the device itself.
        // to find this, use ifconfig | grep inet on terminal
        .baseUrl("http://192.168.1.184:8080/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val authApi : AuthApi = retrofit().create(AuthApi::class.java)
    val tenantApi : TenantApi = retrofit().create(TenantApi::class.java)
    val propertyManagerApi : PropertyManagerApi = retrofit().create(PropertyManagerApi::class.java)
    val issueApi : IssueApi = retrofit().create(IssueApi::class.java)

}