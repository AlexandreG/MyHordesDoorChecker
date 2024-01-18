package fr.zzi.myhordesdoorchecker.settings.data

import fr.zzi.myhordesdoorchecker.settings.domain.SettingsRepositoryInterface

object SettingsRepository : SettingsRepositoryInterface {

    override suspend fun getUserName() = SettingsLocalDataSource.getUserName()
    override suspend fun saveUserName(name: String) = SettingsLocalDataSource.saveUserName(name)
    override suspend fun getUserKey() = SettingsLocalDataSource.getUserKey()
    override suspend fun saveUserId(id: String) = SettingsLocalDataSource.saveUserKey(id)
}