package com.capstone.residentialcommunicationsapp.repositories

import com.capstone.residentialcommunicationsapp.datamodels.BaseRepository
import com.capstone.residentialcommunicationsapp.datamodels.Notifications
import com.capstone.residentialcommunicationsapp.datamodels.NotificationsApi

class NotificationsRepository(private val api: NotificationsApi) : BaseRepository() {

    // this 'suspend' keyword wasn't here originally
    suspend fun getNotifications(): MutableList<Notifications>? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = { api.getNotificationsAsync().await() },
            errorMessage = "Error Fetching Notifications"
        )

        return authResponse?.toMutableList();

    }

    suspend fun getNotificationsByTenantId(tenantId: Int): MutableList<Notifications>? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val authResponse = safeApiCall(
            call = { api.getNotificationsByTenantAsync(tenantId).await() },
            errorMessage = "Error Fetching Notifications"
        )

        return authResponse?.toMutableList();

    }
}