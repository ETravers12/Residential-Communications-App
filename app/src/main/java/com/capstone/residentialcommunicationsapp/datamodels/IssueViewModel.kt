package com.capstone.residentialcommunicationsapp.datamodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication.issueApi
import com.capstone.residentialcommunicationsapp.repositories.IssueRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class IssueViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : IssueRepository = IssueRepository(issueApi)

    val issuesLiveData = MutableLiveData<MutableList<Issue>>()


    fun fetchIssues(){
        scope.launch {
            val issues = repository.getIssues()
            issuesLiveData.postValue(issues)
        }
    }

    fun fetchIssuesByTenantId(tenantId: Int){
        scope.launch {
            val issues = repository.getIssuesByTenantId(tenantId)
            issuesLiveData.postValue(issues)
        }
    }

    fun fetchIssuesByPropertyManagerId(propertyManagerId: Int){
        scope.launch {
            val issues = repository.getIssuesByPropertyManagerId(propertyManagerId)
            issuesLiveData.postValue(issues)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}