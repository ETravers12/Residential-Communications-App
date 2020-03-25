package com.capstone.residentialcommunicationsapp.datamodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication.authApi
import com.capstone.residentialcommunicationsapp.repositories.TenantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TenantViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : TenantRepository = TenantRepository(authApi)


    val tenantUsersLiveData = MutableLiveData<MutableList<Auth>>()

    fun fetchTenantUsers(){
        scope.launch {
            val popularMovies = repository.getTenantUsers()
            tenantUsersLiveData.postValue(tenants)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}