package com.capstone.residentialcommunicationsapp.datamodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication.propertyManagerApi
import com.capstone.residentialcommunicationsapp.repositories.PropertyManagerRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PropertyManagerViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : PropertyManagerRepository = PropertyManagerRepository(propertyManagerApi)


    val propertyManagerUsersLiveData = MutableLiveData<MutableList<PropertyManager>>()

    val propertyManagerLoginLiveData = MutableLiveData<PropertyManager>()

    val propertyManagerCreationLiveData = MutableLiveData<PropertyManager>()


    fun fetchPropertyManagerUsers(){
        scope.launch {
            val propertyManagers = repository.getPropertyManagerUsers()
            propertyManagerUsersLiveData.postValue(propertyManagers)
        }
    }

    fun loginPropertyManager(user: String, pass: String){
        scope.launch {
            val propertyManager = repository.checkPropertyManagerLogin(user, pass)
            propertyManagerLoginLiveData.postValue(propertyManager)
        }
    }

    fun createPropertyManager(name: String, username: String, pass: String){
        scope.launch {
            val propertyManager = repository.createPropertyManager(name, username, pass)
            propertyManagerCreationLiveData.postValue(propertyManager)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}