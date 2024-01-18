package fr.zzi.myhordesdoorchecker.settings.data

import fr.zzi.myhordesdoorchecker.common.SharedPrefUtils

object SettingsLocalDataSource {

    private const val KEY_USER_NAME = "username"
    private const val KEY_USER_KEY = "userkey"
    private const val KEY_USER_ID = "userid"

    suspend fun getUserName() = SharedPrefUtils.getString(KEY_USER_NAME)
    suspend fun saveUserName(value: String) = SharedPrefUtils.saveString(KEY_USER_NAME, value)

    suspend fun getUserKey() = SharedPrefUtils.getString(KEY_USER_KEY)
    suspend fun saveUserKey(value: String) = SharedPrefUtils.saveString(KEY_USER_KEY, value)

    suspend fun getUserId() = SharedPrefUtils.getString(KEY_USER_ID)
    suspend fun saveUserId(value: String) = SharedPrefUtils.saveString(KEY_USER_ID, value)

}