package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.BaseRepository
import com.capstone.residentialcommunicationsapp.datamodels.Issue
import com.capstone.residentialcommunicationsapp.datamodels.IssueApi

class IssueRepository(private val api: IssueApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getIssues(): MutableList<Issue>? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = { api.getIssueAsync().await() },
            errorMessage = "Error Fetching Issue"
        )

        return authResponse?.toMutableList();

    }
}
