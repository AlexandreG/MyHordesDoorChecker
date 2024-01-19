package fr.zzi.myhordesdoorchecker.common

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RestClient {

    private const val URL = "https://myhordes.eu/api/x/json/"
    const val APP_KEY = "fefe0000fefe0000fefe0000fefe0000"
    const val USER_KEY = "fefe0000fefe0000fefe0000fefe0000"

    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().cookieJar(SessionCookieJar()).build())
            .build()
    }

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }


    private class SessionCookieJar : CookieJar {
        private var cookies: List<Cookie>? = null
        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            if (url.encodedPath().endsWith("login")) {
                this.cookies = ArrayList(cookies)
            }
        }

        //TODO MOVE KEYS TO LOCAL.PROPERTIES
        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            return listOf(
                Cookie.Builder().name("myhordes_session_id").value("mi156pl18skm5b5mgifbh3fklb")
                    .domain("myhordes.eu")
                    .build(),
                Cookie.Builder().name("myhordes_remember_me")
                    .value("8766580472b46985c4f4050c416fd853")
                    .domain("myhordes.eu")
                    .build()
            )
        }
    }


}