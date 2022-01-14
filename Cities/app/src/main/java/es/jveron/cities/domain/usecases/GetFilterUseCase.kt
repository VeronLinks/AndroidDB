package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.repository.CityRepository

class GetFilterUseCase(private val cityRepository: CityRepository) {

    fun getFilter(): CityFilter {
        return cityRepository.getCityFilter()
    }

}