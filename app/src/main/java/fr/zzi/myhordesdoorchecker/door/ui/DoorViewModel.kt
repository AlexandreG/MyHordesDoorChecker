package fr.zzi.myhordesdoorchecker.door.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.zzi.myhordesdoorchecker.door.data.DoorRepository
import fr.zzi.myhordesdoorchecker.settings.data.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DoorViewModel : ViewModel() {

    private val doorOpen = MutableLiveData<Boolean?>()
    fun areDoorOpen(): LiveData<Boolean?> = doorOpen

    private val userkeyFilled = MutableLiveData(false)
    fun isUserkeyFilled(): LiveData<Boolean> = userkeyFilled

    private val lastCheckTime = MutableLiveData<Long?>()
    fun getLastCheckTime(): LiveData<Long?> = lastCheckTime

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = SettingsRepository.getUserId()
            val lastDoorStatus = DoorRepository.getLastDoorStatus()
            val lastRequestTime = DoorRepository.getLastRequestTime()

            withContext(Dispatchers.Main) {
                userkeyFilled.value = userId != null
                doorOpen.value = lastDoorStatus
                lastCheckTime.value = lastRequestTime
            }

        }
    }

    fun onCheckDoorStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userkey = SettingsRepository.getUserKey()
                val userId = SettingsRepository.getUserId()
                val mapId = DoorRepository.fetchMapId(userId!!, userkey!!)
                val doorOpen = DoorRepository.fetchIsDoorOpen(mapId, userkey)
                val lastRequest = System.currentTimeMillis()

                DoorRepository.saveLastDoorStatus(doorOpen)
                DoorRepository.saveLastRequestTime(lastRequest)

                withContext(Dispatchers.Main) {
                    userkeyFilled.value = true
                    this@DoorViewModel.doorOpen.value = doorOpen
                    lastCheckTime.value = lastRequest
                }
            } catch (e: Exception) {
                //TODO ERROR HANDLING
                Log.e("", "Unable to fetch map info " + e.message)
            }
        }
    }
}