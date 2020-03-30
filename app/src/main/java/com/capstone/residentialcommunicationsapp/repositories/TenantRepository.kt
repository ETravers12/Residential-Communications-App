package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.*

class TenantRepository(private val api: TenantApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getTenantUsers() : MutableList<Tenant>?{

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = {api.getFakeTenantAsync().await()},
            errorMessage = "Error Fetching User"
        )

        // the second '?' wasn't here before
        return authResponse?.toMutableList();

    }

    suspend fun checkTenantLogin(user: String, pass: String) : Tenant?{
        val userRequest = AuthRequest(user, pass)

        return safeApiCall(
        call = { api.checkLogin(userRequest).await()},
        errorMessage = "Error Fetching User"
    );

    }
}