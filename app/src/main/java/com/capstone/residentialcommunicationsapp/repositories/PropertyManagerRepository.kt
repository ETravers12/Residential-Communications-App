package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.AuthRequest
import com.capstone.residentialcommunicationsapp.datamodels.BaseRepository
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManager
import com.capstone.residentialcommunicationsapp.datamodels.PropertyManagerApi

class PropertyManagerRepository(private val api: PropertyManagerApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getPropertyManagerUsers() : MutableList<PropertyManager>?{

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = {api.getPropertyManagerAsync().await()},
            errorMessage = "Error Fetching User"
        )

        return authResponse?.toMutableList();

    }

    suspend fun checkPropertyManagerLogin(user: String, pass: String) : PropertyManager?{
        val userRequest = AuthRequest(user, pass)

        return safeApiCall(
            call = { api.checkPropertyManagerLogin(userRequest).await()},
            errorMessage = "Error Fetching User"
        );

    }

    suspend fun createPropertyManager(name: String, username: String, pass: String) : PropertyManager? {
        val propertyManager = PropertyManager(0, name, username, pass)

        return safeApiCall(
            call = {api.createPropertyManager(propertyManager).await()},
            errorMessage = "Error Fetching Property Manager"
        );
    }
}