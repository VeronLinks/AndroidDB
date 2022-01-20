package es.jveron.cities.data.repository.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import es.jveron.cities.data.repository.sqlite.CityContract

@Entity(tableName = CityContract.CityEntity.TABLE_NAME)
data class CityAndSightsDbModel (
    @Embedded val city: CityDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "city_id"
    )
    val sights: List<SightDbModel>
)