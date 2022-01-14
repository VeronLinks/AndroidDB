package es.jveron.cities.domain.repository

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun getCities(): List<City>

    fun addCity(city: City)

    suspend fun setCityFilter(cityFilter: CityFilter)

    suspend fun getCityFilter(): Flow<CityFilter>

}