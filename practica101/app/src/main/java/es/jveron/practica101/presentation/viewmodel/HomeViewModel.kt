package es.jveron.practica101.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.jveron.practica101.domain.PracticeData
import es.jveron.practica101.presentation.view.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val practiceMutableStateFlow: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState.Loading)
    val practiceStateFlow: StateFlow<HomeState> = practiceMutableStateFlow
    fun getData() {
        viewModelScope.launch {
            delay(3000) //Simulating network request
            val practiceData = PracticeData("Practice 01 done!")
            practiceMutableStateFlow.emit(HomeState.Success(practiceData))
        }
    }
}