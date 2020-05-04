package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.AuthRequest
import com.capstone.residentialcommunicationsapp.datamodels.BaseRepository
import com.capstone.residentialcommunicationsapp.datamodels.Tenant
import com.capstone.residentialcommunicationsapp.datamodels.TenantApi

class TenantRepository(private val api: TenantApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getTenantUsers() : MutableList<Tenant>?{

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = {api.getTenantAsync().await()},
            errorMessage = "Error Fetching User"
        )

        // the second '?' wasn't here before
        return authResponse?.toMutableList();

    }

    suspend fun checkTenantLogin(user: String, pass: String) : Tenant?{
        val userRequest = AuthRequest(user, pass)

        return safeApiCall(
        call = { api.checkTenantLogin(userRequest).await()},
        errorMessage = "Error Fetching User"
        );

    }

    suspend fun createTenant(name: String, buildNum: Int, unitNum: Int, propId: Int,
                             username: String, pass: String) : Tenant? {
        val tenant = Tenant(name, buildNum, unitNum, propId, username, pass)

        return safeApiCall(
            call = {api.createTenant(tenant).await()},
            errorMessage = "Error Fetching Tenant"
        );
    }
}