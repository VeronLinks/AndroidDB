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
import es.jveron.cities.data.repository.room.CityDao
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_DESCRIPTION
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_NAME
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_SUNSHINE_HOURS
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.TABLE_NAME
import es.jveron.cities.data.repository.sqlite.CitySqliteHelper
import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityAndSights
import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.model.Sight
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
    private val cityService: CityService,
    private val cityDao: CityDao
): CityRepository {


    override suspend fun getCities(): Flow<List<City>> {

        if(shouldRefresh()){
            cityService.getCities().forEach { cityApiModel ->
                val city = CityMapper.mapCityFromApiToDomain(cityApiModel)
                addCity(city)

                cityApiModel.sights.forEach(){
                    val sight = SightMapper.mapSightFromApiToDomain(it)
                    addSight(sight)
                }
            }

            dataStore.edit { mutablePreferences ->
                mutablePreferences[timestamp] = System.currentTimeMillis()
            }
        }

        return cityDao.getCities().map { cityList ->
            cityList.map { city ->
                CityMapper.mapCityFromDbToDomain(city)
            }
        }
    }

    override suspend fun addCity(city: City) {
        val cityToAdd = CityMapper.mapCityFromDomainToDb(city)
        cityDao.insertCity(cityToAdd)

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

    override suspend fun getCityAndSights(cityId: Int): CityAndSights {
        val cityAndSights = cityDao.getCityAndSights(cityId)
        val city = CityMapper.mapCityFromDbToDomain(cityAndSights.city)
        val sights = cityAndSights.sights.map { sight ->
            SightMapper.mapSightFromDbToDomain(sight)
        }
        return CityAndSights(city, sights)
    }

    override suspend fun addSight(sight: Sight) {
        val sightToAdd = SightMapper.mapSightFromDomainToDb(sight)
        cityDao.insertSight(sightToAdd)
    }

    private suspend fun shouldRefresh(): Boolean{
        val timestamp = dataStore.data.map { preference ->
            preference[timestamp] ?: 0
        }.first()

        return System.currentTimeMillis() - timestamp > FIVE_DAYS
    }


}