package es.jveron.cities.data.repository.api

import com.squareup.moshi.Json

class SightApiModel  (
    @field:Json(name = "sight_id") val id: Int,
    @field:Json(name = "city_id") val cityId: Int,
    @field:Json(name = "sight_name") val name: String,
    @field:Json(name = "sight_description") val description: String)