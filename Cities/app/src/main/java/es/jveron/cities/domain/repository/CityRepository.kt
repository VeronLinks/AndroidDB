package es.jveron.cities.domain.repository

import es.jveron.cities.domain.model.City

interface CityRepository {

    fun getCities(): List<City>

    fun addCity(city: City)

}