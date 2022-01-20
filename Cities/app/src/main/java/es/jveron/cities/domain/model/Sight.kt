package es.jveron.cities.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sight (val id: Int, val cityId: Int, val name: String, val description: String) : Parcelable
