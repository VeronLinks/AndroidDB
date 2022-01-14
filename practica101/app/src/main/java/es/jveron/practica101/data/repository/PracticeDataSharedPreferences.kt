package es.jveron.practica101.data.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

private const val PRACTICE_DATA_SHARED_PREF = "practice_data_shared_pref"
private const val PRACTICE_DATA_KEY = "practice_data_key"

class PracticeDataSharedPreferences(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PRACTICE_DATA_SHARED_PREF, MODE_PRIVATE)
    fun getPrefData(): String {
        return sharedPreferences.getString(PRACTICE_DATA_KEY, "") ?: "No data"
    }
    fun addPrefData(value: String) {
        sharedPreferences.edit().putString(PRACTICE_DATA_KEY, value).apply()
    }
    fun deletePrefData() {
        sharedPreferences.edit().remove(PRACTICE_DATA_KEY).apply()
    }
    fun updatePrefData(value: String) {
        sharedPreferences.edit().putString(PRACTICE_DATA_KEY, value).apply()
    }
}