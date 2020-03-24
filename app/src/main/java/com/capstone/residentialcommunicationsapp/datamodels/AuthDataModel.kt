package com.capstone.residentialcommunicationsapp.datamodels

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

data class Auth(
    val password: String,
    val username: String
)

// Data Model for the Response returned from the Auth API
data class AuthResponse(
    val results: List<Auth>
)

//A retrofit Network Interface for the Api
interface AuthApi{
    @GET("user")
    fun getUser(): Deferred<Response<AuthResponse>>
}