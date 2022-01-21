package es.jveron.cities.presentation.viewmodel

import es.jveron.cities.domain.model.CityAndSights

sealed class SightState() {
    object Loading : SightState()
    data class Success(val data: CityAndSights) : SightState()
    data class Failure(val throwable: Throwable) : SightState()
}