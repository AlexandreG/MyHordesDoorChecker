package fr.zzi.myhordesdoorchecker.door.data

import fr.zzi.myhordesdoorchecker.common.RestClient
import retrofit2.http.GET
import retrofit2.http.Query

object MeDataSource {

    private val retrofitService: RetrofitService

    init {
        retrofitService = RestClient.createService(RetrofitService::class.java)
    }

    suspend fun getMeUser(): MeUserData {
        return retrofitService.getMeUser()
    }

    interface RetrofitService {
        @GET("me")
        suspend fun getMeUser(
            @Query("appkey") appKey: String = RestClient.APP_KEY,
            @Query("userkey") userKey: String = RestClient.USER_KEY
        ): MeUserData
    }

    class MeUserData(val id: String)

}