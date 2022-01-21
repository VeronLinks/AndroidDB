package es.jveron.cities.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.jveron.cities.data.repository.CityRepositoryImpl
import es.jveron.cities.data.repository.api.CityService
import es.jveron.cities.data.repository.dataStore
import es.jveron.cities.data.repository.room.CityDatabase
import es.jveron.cities.domain.usecases.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CityAndSightsViewModelFactory (private val context: Context, private val cityId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val cityDao = CityDatabase.getDatabase(context).getDao()
        val repository = CityRepositoryImpl(context.dataStore, createService(), cityDao)
        val getCityAndSightsUseCase = GetCityAndSightsUseCase(repository)
        return CityAndSightsViewModel(cityId, getCityAndSightsUseCase) as T
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