package es.jveron.cities.data.repository

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.repository.CityRepository

class CityRepositoryImpl: CityRepository {

    private val cities = getFakeData()

    override fun getCities(): List<City> {
        val newCities = mutableListOf<City>()
        newCities.addAll(cities)
        return newCities
    }

    override fun addCity(city: City) {
        cities.add(city!!)
    }


    private fun getFakeData() : MutableList<City> {
        val cities = mutableListOf<City>()
        cities.add(City(id = 1, name = "Zaragoza", description = "Guay!!"))
        cities.add(City(id = 1, name = "Madrid", description = "Guay!"))
        cities.add(City(id = 1, name = "Logroño", description = "Guay"))
        return cities
    }

}