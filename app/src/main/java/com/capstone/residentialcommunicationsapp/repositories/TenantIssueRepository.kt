package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.BaseRepository
import com.capstone.residentialcommunicationsapp.datamodels.TenantIssue
import com.capstone.residentialcommunicationsapp.datamodels.TenantIssueApi

class TenantIssueRepository(private val api: TenantIssueApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getTenantIssues(): MutableList<TenantIssue>? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = { api.getTenantIssueAsync().await() },
            errorMessage = "Error Fetching TenantIssue"
        )

        return authResponse?.toMutableList();

    }

    suspend fun getTenantIssuesByPropertyManagerId(propertyManagerId: Int): MutableList<TenantIssue>? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = { api.getTenantIssueByPropertyManagerAsync(propertyManagerId).await() },
            errorMessage = "Error Fetching TenantIssue"
        )

        return authResponse?.toMutableList();

    }
}
