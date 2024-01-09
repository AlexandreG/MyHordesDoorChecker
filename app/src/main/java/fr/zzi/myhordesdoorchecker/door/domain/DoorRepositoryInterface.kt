package fr.zzi.myhordesdoorchecker.door.domain

interface DoorRepositoryInterface {

    suspend fun isDoorOpen(): Boolean

}