package es.jveron.cities.data.repository

import es.jveron.cities.data.repository.api.SightApiModel
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
            sightDbModel.cityId,
            sightDbModel.name,
            sightDbModel.description
        )
    }

    fun mapSightFromDomainToDb (sight: Sight): SightDbModel {
        return SightDbModel(
            id = sight.id,
            cityId = sight.cityId,
            name = sight.name,
            description = sight.description
        )
    }
}