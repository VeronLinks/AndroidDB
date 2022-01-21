package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.CityAndSights
import es.jveron.cities.domain.model.Sight
import es.jveron.cities.domain.repository.CityRepository

class GetCityAndSightsUseCase(val repository: CityRepository) {

    suspend fun getCityAndSights(cityId: Int): CityAndSights {

        return repository.getCityAndSights(cityId)

    }

}