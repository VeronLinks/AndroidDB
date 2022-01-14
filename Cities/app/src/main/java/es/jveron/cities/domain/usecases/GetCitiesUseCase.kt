package es.jveron.cities.domain.usecases

import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.first

class GetCitiesUseCase(private val repository: CityRepository) {

    suspend fun getCities() : List<City> {

        val cities = repository.getCities()
        val filter = repository.getCityFilter().first()

        return when(filter){
            CityFilter.ALL_CITIES -> cities
            CityFilter.SUNNY_CITIES -> cities.filter { city -> city.sunshineHours > 1800 }
            CityFilter.CLOUDY_CITIES -> cities.filter { city -> city.sunshineHours < 1800 }
        }
    }

}