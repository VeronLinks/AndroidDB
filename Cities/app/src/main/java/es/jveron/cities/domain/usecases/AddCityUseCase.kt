package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.repository.CityRepository

class AddCityUseCase (private val cityRepository: CityRepository) {

    suspend fun addCity(city: City) {
        cityRepository.addCity(city)
    }
}