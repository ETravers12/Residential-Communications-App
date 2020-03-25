package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.Auth
import com.capstone.residentialcommunicationsapp.datamodels.AuthApi
import com.capstone.residentialcommunicationsapp.datamodels.BaseRepository

class TenantRepository(private val api : AuthApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getTenantUsers() : MutableList<Auth>?{

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = {api.getUser().await()},
            errorMessage = "Error Fetching User"
        )

        // the second '?' wasn't here before
        return authResponse?.results?.toMutableList();

    }

}