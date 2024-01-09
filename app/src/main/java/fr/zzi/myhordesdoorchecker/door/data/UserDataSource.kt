package fr.zzi.myhordesdoorchecker.door.data

import fr.zzi.myhordesdoorchecker.common.RestClient
import retrofit2.http.GET
import retrofit2.http.Query

object UserDataSource {

    private val retrofitService: RetrofitService

    init {
        retrofitService = RestClient.createService(RetrofitService::class.java)
    }

    suspend fun getUser(userId: String): UserResponse {
        return retrofitService.getUser(userId)
    }

    interface RetrofitService {
        @GET("user")
        suspend fun getUser(
            @Query("id") userId: String,
            @Query("appkey") appKey: String = RestClient.APP_KEY,
            @Query("userkey") userKey: String = RestClient.USER_KEY,
            @Query("fields") field: String = "map"
        ): UserResponse
    }

    data class UserResponse(val map: MapData)
    data class MapData(val id: String)

}