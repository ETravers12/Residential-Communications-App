package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.BaseRepository
import com.capstone.residentialcommunicationsapp.datamodels.Property
import com.capstone.residentialcommunicationsapp.datamodels.PropertyApi

class PropertyRepository(private val api: PropertyApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getProperty(): MutableList<Property>? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = { api.getPropertyAsync().await() },
            errorMessage = "Error Fetching Property"
        )

        return authResponse?.toMutableList();

    }
}