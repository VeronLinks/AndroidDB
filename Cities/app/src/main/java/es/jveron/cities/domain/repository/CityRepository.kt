package es.jveron.cities.domain.repository

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter

interface CityRepository {

    fun getCities(): List<City>

    fun addCity(city: City)

    fun setFilter(cityFilter: CityFilter)

    fun getCityFilter(): CityFilter

}