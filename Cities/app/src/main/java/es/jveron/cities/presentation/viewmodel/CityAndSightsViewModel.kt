package es.jveron.cities.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.jveron.cities.domain.usecases.GetCityAndSightsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class CityAndSightsViewModel(
    private val cityId: Int,
    private val getCityAndSightsUseCase: GetCityAndSightsUseCase
): ViewModel() {

    private val sightsMutableStateFlow = MutableStateFlow<SightState>(SightState.Loading)
    val sightsStateFlow: StateFlow<SightState> = sightsMutableStateFlow

    fun getData(){
        viewModelScope.launch {
            val cityAndSights = getCityAndSightsUseCase.getCityAndSights(cityId)
            sightsMutableStateFlow.emit(SightState.Success(cityAndSights))
        }
    }

}