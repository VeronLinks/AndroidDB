package es.jveron.practica101.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import es.jveron.practica101.data.repository.PreferencesKeys.PRACTICE_NAME_KEY

private const val PRACTICE_DATA_STORE = "practice_data_store"

private val Context.practiceDataStore by preferencesDataStore(
    name = PRACTICE_DATA_STORE
)

private object PreferencesKeys {
    val PRACTICE_NAME_KEY = stringPreferencesKey("practice_name_string")
}

class PracticeDataStore(private val context: Context) {
    fun getDataStoreData(): Flow<String> {
        return context.practiceDataStore.data.map { preferences ->
            val practiceName = preferences[PRACTICE_NAME_KEY] ?: ""
            practiceName
        }
    }

    suspend fun addDataStoreData(value: String) {
        context.practiceDataStore.edit { preferences ->
            preferences[PRACTICE_NAME_KEY] = value
        }
    }

    suspend fun deleteDataStoreData() {
        context.practiceDataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun updateDataStoreData(value: String) {
        context.practiceDataStore.edit { preferences ->
            preferences[PRACTICE_NAME_KEY] = value
        }
    }
}