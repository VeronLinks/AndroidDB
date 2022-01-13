package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.repository.CityRepository

class GetCitiesUseCase(val repository: CityRepository) {

    fun getCities() : List<City> {
        return repository.getCities()
    }

}