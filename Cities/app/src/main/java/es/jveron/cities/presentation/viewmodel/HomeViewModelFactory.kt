package es.jveron.cities.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.jveron.cities.data.repository.CityRepositoryImpl
import es.jveron.cities.data.repository.api.CityService
import es.jveron.cities.data.repository.dataStore
import es.jveron.cities.data.repository.room.CityDatabase
import es.jveron.cities.data.repository.sqlite.CitySqliteHelper
import es.jveron.cities.domain.usecases.AddCityUseCase
import es.jveron.cities.domain.usecases.GetCitiesUseCase
import es.jveron.cities.domain.usecases.GetFilterUseCase
import es.jveron.cities.domain.usecases.SetFilterUseCase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeViewModelFactory (private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val sqliteHelper = CitySqliteHelper(context)
        val cityDao = CityDatabase.getDatabase(context).getDao()
        val repository = CityRepositoryImpl(context.dataStore, sqliteHelper, createService(), cityDao)
        val addCityUseCase = AddCityUseCase(repository)
        val getCitiesUseCase = GetCitiesUseCase(repository)
        val setFilterUseCase = SetFilterUseCase(repository)
        val getFilterUseCase = GetFilterUseCase(repository)
        return HomeViewModel(addCityUseCase, getCitiesUseCase, setFilterUseCase, getFilterUseCase) as T
    }

    private fun createService() : CityService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://ace-ripsaw-338222.oa.r.appspot.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(CityService::class.java)
    }
}