package com.capstone.residentialcommunicationsapp.api

import com.capstone.residentialcommunicationsapp.datamodels.*
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
        .baseUrl("http://residentialcommapp-env.eba-3bzw8zwk.us-east-1.elasticbeanstalk.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val authApi : AuthApi = retrofit().create(AuthApi::class.java)
    val tenantApi : TenantApi = retrofit().create(TenantApi::class.java)
    val propertyManagerApi : PropertyManagerApi = retrofit().create(PropertyManagerApi::class.java)
    val issueApi : IssueApi = retrofit().create(IssueApi::class.java)
    val propertyApi : PropertyApi = retrofit().create(PropertyApi::class.java)
    val maintenanceApi : MaintenanceApi = retrofit().create(MaintenanceApi::class.java)
    val notificationsApi : NotificationsApi = retrofit().create(NotificationsApi::class.java)
}