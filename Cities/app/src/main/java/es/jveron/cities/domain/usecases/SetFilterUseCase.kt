package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.repository.CityRepository

class SetFilterUseCase(private val cityRepository: CityRepository) {

    suspend fun setFilter(cityFilter: CityFilter){
        cityRepository.setCityFilter(cityFilter)
    }
}