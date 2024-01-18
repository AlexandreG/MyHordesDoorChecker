package fr.zzi.myhordesdoorchecker.door.data

import fr.zzi.myhordesdoorchecker.door.domain.DoorRepositoryInterface

object DoorRepository : DoorRepositoryInterface {

    override suspend fun isDoorOpen(): Boolean {
//        val mapId = UserDataSource.getUser(userId).map.id
//        return CityDataSource.getCity(mapId).door
        return false
    }
}