package es.jveron.practica101.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.jveron.practica101.data.repository.PracticeDataRepositoryImpl
import es.jveron.practica101.domain.usecases.AddPracticeData
import es.jveron.practica101.domain.usecases.DeletePracticeData
import es.jveron.practica101.domain.usecases.GetPracticeData
import es.jveron.practica101.domain.usecases.UpdatePracticeData

class HomeViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val practiceDataRepositoryImpl = PracticeDataRepositoryImpl()
        return HomeViewModel(
            getPracticeData = GetPracticeData(practiceDataRepositoryImpl),
            addPracticeData = AddPracticeData(practiceDataRepositoryImpl),
            deletePracticeData = DeletePracticeData(practiceDataRepositoryImpl),
            updatePracticeData = UpdatePracticeData(practiceDataRepositoryImpl)
        ) as T
    }
}