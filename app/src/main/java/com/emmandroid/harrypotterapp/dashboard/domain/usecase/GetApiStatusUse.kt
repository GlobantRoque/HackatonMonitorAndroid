package com.emmandroid.harrypotterapp.dashboard.domain.usecase

import com.emmandroid.harrypotterapp.dashboard.data.repository.ApiStatusRepository
import com.emmandroid.harrypotterapp.dashboard.view.model.ServiceStatus

class GetApiStatusUse(private val apiStatusRepository: ApiStatusRepository) {
    suspend fun getApiStatus(): List<ServiceStatus> {
        return apiStatusRepository.getApiStatusList()
    }
}
