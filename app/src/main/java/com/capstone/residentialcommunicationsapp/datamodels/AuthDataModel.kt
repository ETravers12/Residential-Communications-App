package com.capstone.residentialcommunicationsapp.datamodels

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class Auth(
    val password: String,
    val username: String
)

data class Tenant (
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

data class Property (
    val id: Int,
    val name: String,
    val propertyManagerId: Int
)

data class Maintenance (
    val id: Int,
    val name: String,
    val phoneNumber: String
)

data class Notifications (
    val id: Int,
    val propertyId: Int,
    val message: String
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
    @GET("tenant")
    fun getTenantAsync(): Deferred<Response<List<Tenant>>>

    @POST("tenant/login")
    fun checkTenantLogin(@Body request: AuthRequest): Deferred<Response<Tenant>>

    @POST("tenant/createTenant")
    fun createTenant(@Body tenant: Tenant): Deferred<Response<Tenant>>
}

interface PropertyManagerApi{
    @GET("propertyManager/propertyManager")
    fun getPropertyManagerAsync(): Deferred<Response<List<PropertyManager>>>

    @POST("propertyManager/login")
    fun checkPropertyManagerLogin(@Body request: AuthRequest): Deferred<Response<PropertyManager>>

    // DO I NEED TO HAVE A FORWARD SLASH IN FRONT OF THE TENANT FOR THIS TO WORK????????
    @POST("propertyManager/createPropertyManager")
    fun createPropertyManager(@Body propertyManager: PropertyManager): Deferred<Response<PropertyManager>>
}

interface IssueApi{
    @GET("issue")
    fun getIssueAsync(): Deferred<Response<List<Issue>>>

    @GET("issue")
    fun getIssueByTenantAsync(@Query("tenantId") tenantId: Int): Deferred<Response<List<Issue>>>

    @GET("issue")
    fun getIssueByPropertyManagerAsync(@Query("propertyManagerId") propertyManagerId: Int):
            Deferred<Response<List<Issue>>>

    // FIGURE OUT HOW TO POST ISSUE TO DATABASE!!!!!!!!!!!!
    @POST("issue/createIssue")
    fun createIssue(@Body issue: Issue): Deferred<Response<Issue>>
}

interface PropertyApi{
    @GET("property")
    fun getPropertyAsync(): Deferred<Response<List<Property>>>

    // FIGURE OUT HOW TO POST ISSUE TO DATABASE!!!!!!!!!!!!
    @POST("property/createProperty")
    fun createProperty(@Body property: Property): Deferred<Response<Property>>
}

interface MaintenanceApi{
    @GET("maintenance")
    fun getMaintenanceAsync(): Deferred<Response<List<Maintenance>>>

    @GET("maintenance")
    fun getMaintenanceByPropertyManagerAsync(@Query("propertyManagerId")
                                             propertyManagerId:Int): Deferred<Response<List<Maintenance>>>

    @POST("maintenance/createMaintenance")
    fun createMaintenance(@Body maintenance: Maintenance): Deferred<Response<Maintenance>>
}

interface NotificationsApi{
    @GET("notifications")
    fun getNotificationsAsync(): Deferred<Response<List<Notifications>>>

    @GET("notifications")
    fun getNotificationsByTenantAsync(@Query("tenantId") tenantId: Int):
            Deferred<Response<List<Notifications>>>

    // FIGURE OUT HOW TO POST ISSUE TO DATABASE!!!!!!!!!!!!
    @POST("notifications/createNotification")
    fun createNotification(@Body notification: Notifications): Deferred<Response<Notifications>>
}

interface TenantIssueApi{
    @GET("tenantIssue")
    fun getTenantIssueAsync(): Deferred<Response<List<TenantIssue>>>

    @GET("tenantIssue")
    fun getTenantIssueByPropertyManagerAsync(@Query("propertyManagerId") propertyManagerId: Int):
            Deferred<Response<List<TenantIssue>>>

}
