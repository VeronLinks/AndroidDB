package es.jveron.cities.data.repository

import es.jveron.cities.data.repository.api.SightApiModel
import es.jveron.cities.data.repository.firebase.CitySightFirebaseModel
import es.jveron.cities.data.repository.room.SightDbModel
import es.jveron.cities.domain.model.Sight

object SightMapper {
    fun mapSightFromApiToDomain(sightApiModel: SightApiModel) : Sight {
        return Sight(
            sightApiModel.id,
            sightApiModel.cityId,
            sightApiModel.name,
            sightApiModel.description
        )
    }

    fun mapSightFromDbToDomain(sightDbModel: SightDbModel): Sight {
        return Sight(
            sightDbModel.id,
            sightDbModel.city_id,
            sightDbModel.name,
            sightDbModel.description
        )
    }

    fun mapSightFromFirebaseToDomain(citySightFirebaseModel: CitySightFirebaseModel): Sight {
        return Sight(
            citySightFirebaseModel.id,
            citySightFirebaseModel.cityId,
            citySightFirebaseModel.name,
            citySightFirebaseModel.description
        )
    }

    fun mapSightFromDomainToDb (sight: Sight): SightDbModel {
        return SightDbModel(
            id = sight.id,
            city_id = sight.cityId,
            name = sight.name,
            description = sight.description
        )
    }
}