package com.emmandroid.harrypotterapp.dashboard.data.repository

import com.emmandroid.harrypotterapp.dashboard.data.network.HarryPotterApiClientProvider
import com.emmandroid.harrypotterapp.dashboard.view.model.ApiStatus
import com.emmandroid.harrypotterapp.dashboard.view.model.ServiceStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ApiStatusRepository(private var harryPotterApiClientProvider: HarryPotterApiClientProvider) {

    private val characterId: String = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8"

    suspend fun getApiStatusList(): List<ServiceStatus> {
        return listOf(
            getAllCharactersServiceStatus(),
            getCharacterByIdStatus(),
            getStudentsStatus(),
            getStaffStatus(),
            getCharactersInHouse(),
            getAllSpells(),
        )
    }

    private suspend fun getAllCharactersServiceStatus(): ServiceStatus {
        return withContext(Dispatchers.IO) {
            try {
                val response = harryPotterApiClientProvider.provide().getCharacters()
                getServiceStatus("All Characters", response.isSuccessful)
            } catch (e: Exception) {
                getInactiveServiceStatus("All Characters")
            }
        }
    }

    private suspend fun getCharacterByIdStatus(): ServiceStatus {
        return withContext(Dispatchers.IO) {
            try {
                val response = harryPotterApiClientProvider.provide().getCharacterById(characterId)
                getServiceStatus("Specific Character by ID", response.isSuccessful)
            } catch (e: Exception) {
                getInactiveServiceStatus("Specific Character by ID")
            }
        }
    }

    private suspend fun getStudentsStatus(): ServiceStatus {
        return withContext(Dispatchers.IO) {
            try {
                val response = harryPotterApiClientProvider.provide().getStudents()
                getServiceStatus("Hogwarts Students", response.isSuccessful)
            } catch (e: Exception) {
                getInactiveServiceStatus("Hogwarts Students")
            }
        }
    }

    private suspend fun getStaffStatus(): ServiceStatus {
        return withContext(Dispatchers.IO) {
            try {
                val response = harryPotterApiClientProvider.provide().getStaff()
                getServiceStatus("Hogwarts Staff", response.isSuccessful)
            } catch (e: Exception) {
                getInactiveServiceStatus("Hogwarts Staff")
            }
        }
    }

    private suspend fun getCharactersInHouse(): ServiceStatus {
        return withContext(Dispatchers.IO) {
            try {
                val response = harryPotterApiClientProvider.provide().getCharactersByHouse()
                getServiceStatus("Characters in a House", response.isSuccessful)
            } catch (e: Exception) {
                getInactiveServiceStatus("Characters in a House")
            }
        }
    }

    private suspend fun getAllSpells(): ServiceStatus {
        return withContext(Dispatchers.IO) {
            try {
                val response = harryPotterApiClientProvider.provide().getAllSpells()
                getServiceStatus("All spells", response.isSuccessful)
            } catch (e: Exception) {
                getInactiveServiceStatus("All spells")
            }
        }
    }

    private fun getServiceStatus(endpointName: String, isSuccessful: Boolean): ServiceStatus {
        return if (isSuccessful) {
            ServiceStatus(endpointName, ApiStatus.ACTIVE)
        } else {
            ServiceStatus(endpointName, ApiStatus.INACTIVE)
        }
    }

    private fun getInactiveServiceStatus(endpointName: String): ServiceStatus =
        ServiceStatus(endpointName, ApiStatus.INACTIVE)
}
