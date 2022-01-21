package es.jveron.cities.data.repository.firebase

import es.jveron.cities.data.repository.api.SightApiModel

data class CityFirebaseModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val sunshineHours: Int = 0,
    val sights: List<CitySightFirebaseModel> = emptyList()
){

}
