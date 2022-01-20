package es.jveron.cities.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sights")
data class SightDbModel (
    @PrimaryKey
    val id: Int,
    val cityId: Int,
    val name: String,
    val description: String)