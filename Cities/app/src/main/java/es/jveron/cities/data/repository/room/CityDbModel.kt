package es.jveron.cities.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CityDbModel (
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val sunshineHours: Int)