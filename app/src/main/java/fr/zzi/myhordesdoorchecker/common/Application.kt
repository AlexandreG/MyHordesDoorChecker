package fr.zzi.myhordesdoorchecker.common

import android.app.Application
import android.content.Context


class Application : Application() {

    companion object {
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}