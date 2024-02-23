package com.emmandroid.harrypotterapp.viewmodel

import com.emmandroid.harrypotterapp.data.network.HarryPotterApiClientProvider
import com.emmandroid.harrypotterapp.data.repository.ApiStatusRepository
import com.emmandroid.harrypotterapp.domain.usecase.GetApiStatusUse

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
