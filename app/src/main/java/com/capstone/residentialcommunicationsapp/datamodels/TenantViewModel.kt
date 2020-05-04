package com.capstone.residentialcommunicationsapp.datamodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication.tenantApi
import com.capstone.residentialcommunicationsapp.repositories.TenantRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TenantViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : TenantRepository = TenantRepository(tenantApi)


    val tenantUsersLiveData = MutableLiveData<MutableList<Tenant>>()

    val tenantLoginLiveData = MutableLiveData<Tenant>()

    val tenantCreationLiveData = MutableLiveData<Tenant>()


    fun fetchTenantUsers(){
        scope.launch {
            val tenants = repository.getTenantUsers()
            tenantUsersLiveData.postValue(tenants)
        }
    }

    fun loginTenant(user: String, pass: String){
        scope.launch {
            val tenant = repository.checkTenantLogin(user, pass)
            tenantLoginLiveData.postValue(tenant)
        }
    }

    fun createTenant(name: String, buildNum: Int, unitNum: Int, propId: Int, username: String, pass: String){
        scope.launch {
            val tenant = repository.createTenant(name, buildNum, unitNum, propId, username, pass)
            tenantCreationLiveData.postValue(tenant)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}