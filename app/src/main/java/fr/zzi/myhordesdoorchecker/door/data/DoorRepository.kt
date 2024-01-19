package fr.zzi.myhordesdoorchecker.door.data

import fr.zzi.myhordesdoorchecker.door.domain.DoorRepositoryInterface

object DoorRepository : DoorRepositoryInterface {

    override suspend fun fetchMapId(userId: String, userkey: String): String {
        return UserDataSource.getUser(userId, userkey).map.id
    }

    override suspend fun fetchIsDoorOpen(mapId: String, userkey: String): Boolean {
        return MapDataSource.getCity(mapId, userkey).door
    }

    override suspend fun getLastDoorStatus() = DoorLocalDataSource.getLastDoorStatus()
    override suspend fun saveLastDoorStatus(open: Boolean) =
        DoorLocalDataSource.saveLastDoorStatus(open)

    override suspend fun getLastRequestTime() = DoorLocalDataSource.getLastRequestTime()
    override suspend fun saveLastRequestTime(time: Long) =
        DoorLocalDataSource.saveLastRequestTime(time)
}