package es.jveron.cities.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City (
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val sunshineHours: Int)