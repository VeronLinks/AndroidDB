package es.jveron.practica101.presentation.view

import es.jveron.practica101.domain.PracticeData

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val practiceData: PracticeData) : HomeState()
    data class Failure(val exception: Throwable) : HomeState()
}