package fr.zzi.myhordesdoorchecker.common

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SharedPrefUtils {

    private const val fileName = "settings"

    fun getBoolean(key: String) =
        Application.context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            .getBoolean(key, false)

    fun getInt(key: String) =
        Application.context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            .getInt(key, 0)

    fun getString(key: String) =
        Application.context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            .getString(key, null)

    fun saveString(key: String, value: String) =
        Application.context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit {
            putString(key, value)
        }

    fun saveInt(key: String, value: Int) {
        Application.context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit {
            putInt(key, value)
        }
    }

    fun saveBoolean(key: String, value: Boolean) {
        Application.context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit {
            putBoolean(key, value)
        }
    }

    private fun getSharedPrefEditor(pref: String?): SharedPreferences {
        return Application.context.getSharedPreferences(pref, Context.MODE_PRIVATE)
    }

}