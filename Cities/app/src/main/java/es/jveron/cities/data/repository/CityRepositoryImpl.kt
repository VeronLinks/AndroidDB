package es.jveron.cities.data.repository

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.provider.Settings
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import es.jveron.cities.data.repository.CityKey.cityFilterKey
import es.jveron.cities.data.repository.CityKey.timestamp
import es.jveron.cities.data.repository.api.CityService
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_DESCRIPTION
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_NAME
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_SUNSHINE_HOURS
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.TABLE_NAME
import es.jveron.cities.data.repository.sqlite.CitySqliteHelper
import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

const val CITY_FILTER_KEY = "CityFilterKey"
const val TIMESTAMP = "timeStamp"

const val FIVE_DAYS = 432000000

const val CITY_DATA_STORE = "CITY_DATA_STORE"

val Context.dataStore by preferencesDataStore(
    name = CITY_DATA_STORE
)

object CityKey{
    val cityFilterKey = stringPreferencesKey(CITY_FILTER_KEY)
    val timestamp = longPreferencesKey(TIMESTAMP)
}

class CityRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val citySqliteHelper: CitySqliteHelper,
    private val cityService: CityService): CityRepository {

    private val db = citySqliteHelper.writableDatabase

    override suspend fun getCities(): List<City> {

        if (shouldRefresh()) {
            cityService.getCities().forEach { cityApiModel ->
                val city = CityMapper.mapCityFromApiToDomain(cityApiModel)
                addCity(city)
            }

            dataStore.edit { mutablePreferences ->
                mutablePreferences[timestamp] = System.currentTimeMillis()
            }
        }

        val cities = mutableListOf<City>()

        Log.d("CITY_REPOSITORY", Thread.currentThread().name)

        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        with(cursor){
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val sunshineHours = getInt(getColumnIndexOrThrow(COLUMN_SUNSHINE_HOURS))

                cities.add(City(id, name, description, sunshineHours))
            }
        }

        return cities
    }

    override fun addCity(city: City) {
        val cityContentValues = ContentValues()
        cityContentValues.put(BaseColumns._ID, city.id)
        cityContentValues.put(COLUMN_NAME, city.name)
        cityContentValues.put(COLUMN_DESCRIPTION, city.description)
        cityContentValues.put(COLUMN_SUNSHINE_HOURS, city.sunshineHours)

        db.insert(TABLE_NAME, null, cityContentValues)
    }

    override suspend fun setCityFilter(cityFilter: CityFilter) {

        dataStore.edit { preferences ->
            preferences[cityFilterKey] = cityFilter.name
        }

    }

    override suspend fun getCityFilter(): Flow<CityFilter> {
        return dataStore.data.map { preferences ->
            val filter = preferences[cityFilterKey] ?: CityFilter.ALL_CITIES.name
            CityFilter.valueOf(filter)
        }
    }

    private suspend fun shouldRefresh() : Boolean {
        val timestamp = dataStore.data.map { preference ->
            preference[timestamp] ?: 0
        }.first()

        return System.currentTimeMillis() - timestamp > FIVE_DAYS
    }

}