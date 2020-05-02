package com.capstone.residentialcommunicationsapp.datamodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication.tenantIssueApi
import com.capstone.residentialcommunicationsapp.repositories.TenantIssueRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TenantIssueViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : TenantIssueRepository = TenantIssueRepository(tenantIssueApi)

    val tenantIssuesLiveData = MutableLiveData<MutableList<TenantIssue>>()


    fun fetchTenantIssues(){
        scope.launch {
            val tenantIssues = repository.getTenantIssues()
            tenantIssuesLiveData.postValue(tenantIssues)
        }
    }

    fun fetchTenantIssuesByPropertyManagerId(propertyManagerId: Int){
        scope.launch {
            val tenantIssues = repository.getTenantIssuesByPropertyManagerId(propertyManagerId)
            tenantIssuesLiveData.postValue(tenantIssues)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}