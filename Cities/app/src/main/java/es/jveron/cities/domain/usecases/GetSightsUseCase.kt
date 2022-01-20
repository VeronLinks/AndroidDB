package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.Sight
import es.jveron.cities.domain.repository.CityRepository

class GetSightsUseCase(val repository: CityRepository) {

    suspend fun getSights(cityId: Int): List<Sight> {

        return repository.getSights(cityId)

    }

}