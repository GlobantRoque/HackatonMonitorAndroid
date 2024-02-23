package com.emmandroid.harrypotterapp.dashboard.viewmodel

import com.emmandroid.harrypotterapp.dashboard.data.network.HarryPotterApiClientProvider
import com.emmandroid.harrypotterapp.dashboard.data.repository.ApiStatusRepository
import com.emmandroid.harrypotterapp.dashboard.domain.usecase.GetApiStatusUse

class ApiListViewFactoryProvider {

    private val harryPotterApiClientProvider by lazy {
        HarryPotterApiClientProvider()
    }

    private val apiStatusRepository by lazy {
        ApiStatusRepository(harryPotterApiClientProvider)
    }

    private val getApiStatusUse by lazy {
        GetApiStatusUse(apiStatusRepository)
    }

    private val apiListViewModelFactory by lazy {
        ApiListViewModelFactory(getApiStatusUse)
    }

    fun provide(): ApiListViewModelFactory {
        return apiListViewModelFactory
    }
}
