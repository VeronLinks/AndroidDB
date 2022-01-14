package es.jveron.practica101.domain.usecases

import es.jveron.practica101.domain.PracticeRepository

class DeletePracticeData(private val practiceRepository: PracticeRepository) {
    fun deletePracticeData() {
        return practiceRepository.deletePracticeData()
    }
}