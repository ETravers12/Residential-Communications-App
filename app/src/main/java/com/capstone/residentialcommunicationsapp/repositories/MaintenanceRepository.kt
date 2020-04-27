package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.BaseRepository
import com.capstone.residentialcommunicationsapp.datamodels.Maintenance
import com.capstone.residentialcommunicationsapp.datamodels.MaintenanceApi

class MaintenanceRepository(private val api: MaintenanceApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getMaintenance(): MutableList<Maintenance>? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = { api.getMaintenanceAsync().await() },
            errorMessage = "Error Fetching Maintenance"
        )

        return authResponse?.toMutableList();

    }

    suspend fun getMaintenanceByPropertyManagerId(propertyManagerId:Int): MutableList<Maintenance>? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = { api.getMaintenanceByPropertyManagerAsync(propertyManagerId).await() },
            errorMessage = "Error Fetching Maintenance"
        )

        return authResponse?.toMutableList();

    }
}