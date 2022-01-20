package es.jveron.cities.data.repository

import es.jveron.cities.data.repository.api.CityApiModel
import es.jveron.cities.data.repository.room.CityDbModel
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

    fun mapCityFromDbToDomain(cityDbModel: CityDbModel): City {
        return City(
            cityDbModel.id,
            cityDbModel.name,
            cityDbModel.description,
            cityDbModel.sunshineHours
        )
    }

    fun mapCityFromDomainToDb (city: City): CityDbModel {
        return CityDbModel(
            id = city.id,
            name = city.name,
            description = city.description,
            sunshineHours = city.sunshineHours
        )
    }

}