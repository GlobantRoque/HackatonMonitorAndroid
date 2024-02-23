package com.emmandroid.harrypotterapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emmandroid.harrypotterapp.domain.usecase.GetApiStatusUse

class ApiListViewModelFactory(
    private val getApiStatusUse: GetApiStatusUse,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiListViewModel::class.java)) {
            return ApiListViewModel(getApiStatusUse) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
