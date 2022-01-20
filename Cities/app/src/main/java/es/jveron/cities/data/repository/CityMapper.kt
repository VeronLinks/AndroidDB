package es.jveron.cities.data.repository

import es.jveron.cities.data.repository.api.CityApiModel
import es.jveron.cities.domain.model.City

object CityMapper {
    fun mapCityFromApiToDomain(cityApiModel: CityApiModel) : City {
        return City(
            cityApiModel.id,
            cityApiModel.name,
            cityApiModel.description,
            cityApiModel.sunshineHours
        )
    }

}