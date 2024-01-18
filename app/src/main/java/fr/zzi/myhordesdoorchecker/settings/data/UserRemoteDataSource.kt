package fr.zzi.myhordesdoorchecker.settings.data

import fr.zzi.myhordesdoorchecker.common.RestClient
import retrofit2.http.GET
import retrofit2.http.Query

object MeDataSource {

    private val retrofitService: RetrofitService

    init {
        retrofitService = RestClient.createService(RetrofitService::class.java)
    }

    suspend fun getUser(userKey: String): UserData {
        return retrofitService.getUser(userKey)
    }

    interface RetrofitService {
        @GET("me")
        suspend fun getUser(
            @Query("userkey") userKey: String,
            @Query("appkey") appKey: String = RestClient.APP_KEY
        ): UserData
    }

    class UserData(
        val id: String,
        val name: String
    )

}