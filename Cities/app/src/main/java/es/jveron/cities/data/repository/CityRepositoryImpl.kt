package es.jveron.cities.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import es.jveron.cities.data.repository.CityFilterKey.cityFilterKey
import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

const val CITIES_PREFERENCES = "Cities_Preferences"
const val CITY_FILTER_KEY = "CityFilterKey"

const val CITIES_DATA_STORE = "CITIES_DATA_STORAGE"

val Context.dataStore by preferencesDataStore(
    name = CITIES_DATA_STORE
)

object CityFilterKey {
    val cityFilterKey = stringPreferencesKey(CITY_FILTER_KEY)
}

class CityRepositoryImpl(private val dataStore: DataStore<Preferences>): CityRepository {

    private val cities = getFakeData()

    override fun getCities(): List<City> {
        val newCities = mutableListOf<City>()
        newCities.addAll(cities)
        return newCities
    }

    override fun addCity(city: City) {
        cities.add(city!!)
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

    private fun getFakeData(): MutableList<City> {
        val cities = mutableListOf<City>()
        cities.add(City(1, "Zaragoza", "Zaragoza es una ciudad y un municipio de España, capital de la provincia homónima y de la comunidad autónoma de Aragón. ", 2690))
        cities.add(City(2, "Londres", "Londres es la capital y mayor ciudad de Inglaterra y del Reino Unido.",1410))
        cities.add(City(3, "Berlín", "Berlín es la capital de Alemania y uno de los dieciséis estados federados alemanes. ",1625))
        cities.add(City(4, "Santo Domingo", "Santo Domingo (oficialmente llamada Santo Domingo de Guzmán) es la capital y ciudad más poblada de la República Dominicana.",2316))
        return cities
    }

}