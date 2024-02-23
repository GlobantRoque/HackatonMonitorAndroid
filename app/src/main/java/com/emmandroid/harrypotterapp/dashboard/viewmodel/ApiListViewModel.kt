package com.emmandroid.harrypotterapp.dashboard.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmandroid.harrypotterapp.dashboard.domain.usecase.GetApiStatusUse
import com.emmandroid.harrypotterapp.dashboard.view.model.ServiceStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApiListViewModel(private val getApiStatusUse: GetApiStatusUse) : ViewModel() {

    private var _apiStatusList: MutableStateFlow<List<ServiceStatus>> = MutableStateFlow(emptyList())
    var apiStatusList = _apiStatusList.asStateFlow()

    private var _statusUpdateTimeDate: MutableStateFlow<Long> = MutableStateFlow(System.currentTimeMillis())
    var statusUpdateTimeDate = _statusUpdateTimeDate.asStateFlow()

    private var _timeUntilNextUpdate: MutableStateFlow<String> = MutableStateFlow("5:00 min")
    var timeUntilNextUpdate = _timeUntilNextUpdate.asStateFlow()

    fun onViewCreated() {
        viewModelScope.launch {
            _apiStatusList.value = getApiStatusUse.getApiStatus()
        }
    }

    fun onServiceStatusUpdate() {
        _statusUpdateTimeDate.value = System.currentTimeMillis()
        startCountDownTimer()
    }

    private fun startCountDownTimer() {
        val timer = object : CountDownTimer(30_000, 1000) {
            override fun onTick(timeLeftMillis: Long) {
                val minutes = ((timeLeftMillis / 1000) / 60).toInt()
                val seconds = ((timeLeftMillis / 1000) % 60)
                _timeUntilNextUpdate.value = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                viewModelScope.launch {
                    _apiStatusList.value = getApiStatusUse.getApiStatus()
                    _statusUpdateTimeDate.value = System.currentTimeMillis()
                    startCountDownTimer()
                }
            }
        }
        timer.start()
    }
}
