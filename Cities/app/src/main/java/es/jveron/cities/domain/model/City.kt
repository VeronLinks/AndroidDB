package es.jveron.cities.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City (val id: Int, val name: String, val description: String) : Parcelable