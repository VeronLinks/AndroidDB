package es.jveron.cities.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.jveron.cities.data.repository.CITIES_PREFERENCES
import es.jveron.cities.data.repository.CityRepositoryImpl
import es.jveron.cities.domain.usecases.AddCityUseCase
import es.jveron.cities.domain.usecases.GetCitiesUseCase
import es.jveron.cities.domain.usecases.GetFilterUseCase
import es.jveron.cities.domain.usecases.SetFilterUseCase

class HomeViewModelFactory (private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = CityRepositoryImpl(context.getSharedPreferences(CITIES_PREFERENCES, Context.MODE_PRIVATE))
        val addCityUseCase = AddCityUseCase(repository)
        val getCitiesUseCase = GetCitiesUseCase(repository)
        val setFilterUseCase = SetFilterUseCase(repository)
        val getFilterUseCase = GetFilterUseCase(repository)
        return HomeViewModel(addCityUseCase, getCitiesUseCase, setFilterUseCase, getFilterUseCase) as T
    }
}