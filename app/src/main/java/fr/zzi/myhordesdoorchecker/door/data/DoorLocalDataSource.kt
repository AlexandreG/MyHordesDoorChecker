package fr.zzi.myhordesdoorchecker.door.data

import fr.zzi.myhordesdoorchecker.common.SharedPrefUtils

object DoorLocalDataSource {

    private const val KEY_LAST_REQUEST_TIME = "last_request_time"
    private const val KEY_LAST_DOOR_STATUS = "last_door_status"

    suspend fun getLastDoorStatus() = SharedPrefUtils.getBoolean(KEY_LAST_DOOR_STATUS)
    suspend fun saveLastDoorStatus(value: Boolean) =
        SharedPrefUtils.saveBoolean(KEY_LAST_DOOR_STATUS, value)

    suspend fun getLastRequestTime() = SharedPrefUtils.getLong(KEY_LAST_REQUEST_TIME)
    suspend fun saveLastRequestTime(value: Long) =
        SharedPrefUtils.saveLong(KEY_LAST_REQUEST_TIME, value)

}