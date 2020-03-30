package com.capstone.residentialcommunicationsapp.datamodels

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class Auth(
    val password: String,
    val username: String
)

data class Tenant (
    val id: Int,
    val tenantName: String,
    val buildNum: Int,
    val unitNum: Int,
    val propId: Int,
    val username: String,
    val password: String

)

data class AuthRequest(
    val username: String,
    val password: String
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
interface TenantApi{
    @GET("tenant/fakeTenant")
    fun getFakeTenantAsync(): Deferred<Response<List<Tenant>>>

    @POST("tenant/login")
    fun checkLogin(@Body request: AuthRequest): Deferred<Response<Tenant>>
}
