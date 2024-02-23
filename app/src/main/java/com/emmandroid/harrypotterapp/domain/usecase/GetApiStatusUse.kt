package com.emmandroid.harrypotterapp.domain.usecase

import com.emmandroid.harrypotterapp.data.repository.ApiStatusRepository
import com.emmandroid.harrypotterapp.view.model.ServiceStatus

class GetApiStatusUse(private val apiStatusRepository: ApiStatusRepository) {
    suspend fun getApiStatus(): List<ServiceStatus> {
        return apiStatusRepository.getApiStatusList()
    }
}
