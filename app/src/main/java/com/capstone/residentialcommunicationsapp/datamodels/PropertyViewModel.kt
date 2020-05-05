package com.capstone.residentialcommunicationsapp.datamodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication.propertyApi
import com.capstone.residentialcommunicationsapp.repositories.PropertyRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PropertyViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : PropertyRepository = PropertyRepository(propertyApi)

    val propertyLiveData = MutableLiveData<MutableList<Property>>()

    val propertyCreationLiveData = MutableLiveData<Property>()


    fun fetchProperty(){
        scope.launch {
            val property = repository.getProperty()
            propertyLiveData.postValue(property)
        }
    }

    fun fetchPropertyByPropertyManagerId(propertyManagerId: Int){
        scope.launch {
            val property = repository.getPropertyByPropertyManagerId(propertyManagerId)
            propertyLiveData.postValue(property)
        }
    }

    fun createProperty(name: String, propertyManagerId: Int){
        scope.launch {
            val property = repository.createProperty(name, propertyManagerId)
            propertyCreationLiveData.postValue(property)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
