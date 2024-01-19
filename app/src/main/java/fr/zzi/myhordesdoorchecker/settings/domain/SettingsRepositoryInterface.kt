package fr.zzi.myhordesdoorchecker.settings.domain

interface SettingsRepositoryInterface {

    suspend fun getUserName(): String?
    suspend fun saveUserName(name: String)
    suspend fun getUserKey(): String?
    suspend fun saveUserKey(key: String)
    suspend fun getUserId(): String?
    suspend fun saveUserId(id: String)
}