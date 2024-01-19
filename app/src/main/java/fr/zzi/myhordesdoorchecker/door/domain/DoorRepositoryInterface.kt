package fr.zzi.myhordesdoorchecker.door.domain

interface DoorRepositoryInterface {

    suspend fun fetchMapId(userId: String, userkey: String): String
    suspend fun fetchIsDoorOpen(mapId: String, userkey: String): Boolean

    suspend fun getLastDoorStatus(): Boolean
    suspend fun saveLastDoorStatus(open: Boolean)
    suspend fun getLastRequestTime(): Long
    suspend fun saveLastRequestTime(time: Long)

}