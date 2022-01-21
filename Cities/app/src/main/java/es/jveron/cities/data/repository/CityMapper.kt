package es.jveron.cities.data.repository

import es.jveron.cities.data.repository.api.CityApiModel
import es.jveron.cities.data.repository.firebase.CityFirebaseModel
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

    fun mapCityFromFirebaseToDomain (cityFirebaseModel: CityFirebaseModel): City {
        return City(
            id = cityFirebaseModel.id,
            name = cityFirebaseModel.name,
            description = cityFirebaseModel.description,
            sunshineHours = cityFirebaseModel.sunshineHours
        )
    }

    fun mapCityFromDomainToFirebase (city: City): CityFirebaseModel {
        return CityFirebaseModel(
            id = city.id,
            name = city.name,
            description = city.description,
            sunshineHours = city.sunshineHours
        )
    }
}