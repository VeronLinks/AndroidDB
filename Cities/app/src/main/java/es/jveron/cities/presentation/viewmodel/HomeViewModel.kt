package es.jveron.cities.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.usecases.AddCityUseCase
import es.jveron.cities.domain.usecases.GetCitiesUseCase
import es.jveron.cities.domain.usecases.GetFilterUseCase
import es.jveron.cities.domain.usecases.SetFilterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (
    private val addCityUseCase: AddCityUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val setFilterUseCase: SetFilterUseCase,
    private val getFilterUseCase: GetFilterUseCase
    ) : ViewModel() {

    private val citiesMutableStateFlow = MutableStateFlow<CityState>(CityState.Loading)
    val citiesStateFlow : StateFlow<CityState> = citiesMutableStateFlow

    private val filterCityMutableStateFlow = MutableStateFlow(CityFilter.ALL_CITIES)
    val filterCityState : StateFlow<CityFilter> = filterCityMutableStateFlow

    fun getData() {

        viewModelScope.launch(Dispatchers.IO) {
            val newCities = getCitiesUseCase.getCities()
            citiesMutableStateFlow.emit(CityState.Success(newCities))
        }
    }

    fun addCity(city: City?) {
        viewModelScope.launch(Dispatchers.IO) {
            addCityUseCase.addCity(city!!)
            getData()
        }
    }

    fun setFilter(cityFilter: CityFilter){
        viewModelScope.launch {
            setFilterUseCase.setFilter(cityFilter)
            getData()
            getFilter()
        }
    }

    fun getFilter() {
        viewModelScope.launch {
            getFilterUseCase.getFilter().collect { filter ->
                filterCityMutableStateFlow.emit(filter)
            }
        }
    }

}