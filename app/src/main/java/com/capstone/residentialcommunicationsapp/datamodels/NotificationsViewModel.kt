package com.capstone.residentialcommunicationsapp.datamodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.residentialcommunicationsapp.api.ApiForAuthentication.notificationsApi
import com.capstone.residentialcommunicationsapp.repositories.NotificationsRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NotificationsViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : NotificationsRepository = NotificationsRepository(notificationsApi)

    val notificationsLiveData = MutableLiveData<MutableList<Notifications>>()


    fun fetchNotifications(){
        scope.launch {
            val notifications = repository.getNotifications()
            notificationsLiveData.postValue(notifications)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}