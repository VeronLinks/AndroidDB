package es.jveron.cities.data.repository.firebase

import com.google.firebase.database.PropertyName

data class CitySightFirebaseModel(
    val id: Int = 0,
    val cityId: Int = 0,
    @get:PropertyName("sight_name") @set:PropertyName("sight_name") var name: String = "",
    @get:PropertyName("sight_description") @set:PropertyName("sight_description") var description: String = ""){

}