package com.capstone.residentialcommunicationsapp.datamodels

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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

data class PropertyManager (
    val id: Int,
    val propertyName: String,
    val username: String,
    val password: String
)

data class Issue (
    val id: Int,
    val type: String,
    val description: String,
    val urgencyLevel: Int,
    val tenantId: Int,
    val maintenanceId: Int,
    val status: String
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
    fun getTenantAsync(): Deferred<Response<List<Tenant>>>

    @POST("tenant/login")
    fun checkTenantLogin(@Body request: AuthRequest): Deferred<Response<Tenant>>
}

interface PropertyManagerApi{
    @GET("propertyManager/propertyManager")
    fun getPropertyManagerAsync(): Deferred<Response<List<PropertyManager>>>

    @POST("propertyManager/login")
    fun checkPropertyManagerLogin(@Body request: AuthRequest): Deferred<Response<PropertyManager>>
}

interface IssueApi{
    @GET("issue/issue")
    fun getIssueAsync(): Deferred<Response<Issue>>

    // FIGURE OUT HOW TO POST ISSUE TO DATABASE!!!!!!!!!!!!
    @POST("issue/createIssue")
    fun createIssue()
}
