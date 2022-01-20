package es.jveron.cities.data.repository.api

import android.os.Parcelable
import com.squareup.moshi.Json

class CityApiModel  (
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "sunshine_hours") val sunshineHours: Int)