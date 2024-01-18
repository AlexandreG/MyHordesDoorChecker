package fr.zzi.myhordesdoorchecker.settings.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun onUserkeyFilled(userkey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = MeDataSource.getUser(userkey)
            SettingsRepository.saveUserId(user.id)
            SettingsRepository.saveUserName(user.name)

            withContext(Dispatchers.Main) {
                username.value = user.name
            }
        }
    }

    fun loadInitialState(): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            val userkeyResult = SettingsRepository.getUserKey()
            val usernameResult = SettingsRepository.getUserName()
            withContext(Dispatchers.Main) {
                userkey.value = userkeyResult
                username.value = usernameResult
            }
        }
        return true
    }
}