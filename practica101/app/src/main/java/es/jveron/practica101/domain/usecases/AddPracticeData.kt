package es.jveron.practica101.domain.usecases

import es.jveron.practica101.domain.PracticeData
import es.jveron.practica101.domain.PracticeRepository

class AddPracticeData(private val practiceRepository: PracticeRepository) {
    suspend fun addPracticeData(practiceData: PracticeData) {
        return practiceRepository.addPracticeData(practiceData)
    }
}