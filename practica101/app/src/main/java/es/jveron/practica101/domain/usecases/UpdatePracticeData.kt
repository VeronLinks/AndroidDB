package es.jveron.practica101.domain.usecases

import es.jveron.practica101.domain.PracticeData
import es.jveron.practica101.domain.PracticeRepository

class UpdatePracticeData(private val practiceRepository: PracticeRepository) {
    fun updatePracticeData(practiceData: PracticeData) {
        return practiceRepository.updatePracticeData(practiceData)
    }
}