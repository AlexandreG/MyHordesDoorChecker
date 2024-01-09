package fr.zzi.myhordesdoorchecker.door.data

import fr.zzi.myhordesdoorchecker.common.RestClient
import retrofit2.http.GET
import retrofit2.http.Query

object CityDataSource {

    private val retrofitService: RetrofitService

    init {
        retrofitService = RestClient.createService(RetrofitService::class.java)
    }

    suspend fun getCity(mapId: String): CityData {
        return retrofitService.getCity(mapId).city
    }

    interface RetrofitService {
        @GET("map")
        suspend fun getCity(
            @Query("mapId") mapId: String,
            @Query("appkey") appKey: String = RestClient.APP_KEY,
            @Query("userkey") userKey: String = RestClient.USER_KEY,
            @Query("fields") field: String = "city"
        ): CityResult
    }

    class CityResult(val city: CityData)
    class CityData(val door: Boolean)

}