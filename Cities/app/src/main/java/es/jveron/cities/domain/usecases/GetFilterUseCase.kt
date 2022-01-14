package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow

class GetFilterUseCase(private val cityRepository: CityRepository) {

    suspend fun getFilter(): Flow<CityFilter> {
        return cityRepository.getCityFilter()
    }

}