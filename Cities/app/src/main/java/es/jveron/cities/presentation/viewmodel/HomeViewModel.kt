package es.jveron.cities.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.usecases.AddCityUseCase
import es.jveron.cities.domain.usecases.GetCitiesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel (
    private val addCityUseCase: AddCityUseCase,
    private val getCitiesUseCase: GetCitiesUseCase
    ) : ViewModel() {

    private val citiesMutableStateFlow = MutableStateFlow<CityState>(CityState.Loading)
    val citiesStateFlow : StateFlow<CityState> = citiesMutableStateFlow

    fun getData() {

        viewModelScope.launch {
            delay(1000)
            val newCities = getCitiesUseCase.getCities()
            citiesMutableStateFlow.emit(CityState.Success(newCities))
        }

    }

    fun addCity(city: City?) {
        addCityUseCase.addCity(city!!)
        getData()
    }

}