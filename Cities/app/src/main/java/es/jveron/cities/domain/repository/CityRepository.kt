package es.jveron.cities.domain.repository

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun getCities(): Flow<List<City>>

    suspend fun addCity(city: City)

    suspend fun setCityFilter(cityFilter: CityFilter)

    suspend fun getCityFilter(): Flow<CityFilter>

}