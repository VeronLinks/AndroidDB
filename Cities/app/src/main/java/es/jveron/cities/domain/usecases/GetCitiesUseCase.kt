package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class GetCitiesUseCase(val repository: CityRepository) {

    suspend fun getCities(): Flow<List<City>> {

        val filter = repository.getCityFilter().first()
        return repository.getCities().map { cityList ->
            when(filter){
                CityFilter.ALL_CITIES -> cityList
                CityFilter.SUNNY_CITIES -> cityList.filter { city -> city.sunshineHours >= 1800 }
                CityFilter.CLOUDY_CITIES -> cityList.filter { city -> city.sunshineHours < 1800 }
                else -> cityList
            }
        }


    }

}