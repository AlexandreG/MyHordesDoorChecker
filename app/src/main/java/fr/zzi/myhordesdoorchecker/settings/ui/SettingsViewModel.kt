package fr.zzi.myhordesdoorchecker.settings.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.zzi.myhordesdoorchecker.common.RestClient
import fr.zzi.myhordesdoorchecker.settings.data.MeDataSource
import fr.zzi.myhordesdoorchecker.settings.data.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel : ViewModel() {

    private val username = MutableLiveData("")
    fun getUsername(): LiveData<String> = username

    private val userkey = MutableLiveData("")
    fun getUserkey(): LiveData<String> = userkey

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userkeyResult = SettingsRepository.getUserKey() ?: RestClient.USER_KEY
            val usernameResult = SettingsRepository.getUserName()
            withContext(Dispatchers.Main) {
                userkey.value = userkeyResult
                username.value = usernameResult
            }
        }
    }

    fun onSendUserkey(userkey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            SettingsRepository.saveUserKey(userkey)
            val user = try {
                MeDataSource.getUser(userkey)
            } catch (e: Exception) {
                //TODO ERROR HANDLING

                Log.e("", "Unable to fetch user info " + e.message)
                null
            }
            SettingsRepository.saveUserId(user?.id ?: "")
            SettingsRepository.saveUserName(user?.name ?: "")

            withContext(Dispatchers.Main) {
                username.value = user?.name ?: ""
            }
        }
    }
}