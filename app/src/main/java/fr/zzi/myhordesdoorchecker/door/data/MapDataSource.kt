package fr.zzi.myhordesdoorchecker.door.data

import fr.zzi.myhordesdoorchecker.common.RestClient
import retrofit2.http.GET
import retrofit2.http.Query

object MapDataSource {

    private val retrofitService: RetrofitService

    init {
        retrofitService = RestClient.createService(RetrofitService::class.java)
    }

    suspend fun getCity(mapId: String, userkey: String): CityData {
        return retrofitService.getCity(mapId, userkey).city
    }

    interface RetrofitService {
        @GET("map")
        suspend fun getCity(
            @Query("mapId") mapId: String,
            @Query("userkey") userKey: String,
            @Query("appkey") appKey: String = RestClient.APP_KEY,
            @Query("fields") field: String = "city"
        ): CityResult
    }

    class CityResult(val city: CityData)
    class CityData(val door: Boolean)

}