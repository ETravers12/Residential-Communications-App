package com.capstone.residentialcommunicationsapp.datamodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication.maintenanceApi
import com.capstone.residentialcommunicationsapp.repositories.MaintenanceRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MaintenanceViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : MaintenanceRepository = MaintenanceRepository(maintenanceApi)

    val maintenanceLiveData = MutableLiveData<MutableList<Maintenance>>()


    fun fetchMaintenance(){
        scope.launch {
            val maintenance = repository.getMaintenance()
            maintenanceLiveData.postValue(maintenance)
        }
    }

    fun fetchMaintenanceByPropertyManagerId(propertyManagerId:Int){
        scope.launch {
            val maintenance = repository.getMaintenanceByPropertyManagerId(propertyManagerId)
            maintenanceLiveData.postValue(maintenance)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}