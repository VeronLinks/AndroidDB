package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.Sight
import es.jveron.cities.domain.repository.CityRepository

class AddSightUseCase (private val cityRepository: CityRepository) {

    suspend fun addSight(sight: Sight) {
        cityRepository.addSight(sight)
    }
}