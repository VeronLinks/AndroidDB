package es.jveron.practica101.domain.usecases

import es.jveron.practica101.domain.PracticeRepository

class DeletePracticeData(private val practiceRepository: PracticeRepository) {
    suspend fun deletePracticeData() {
        return practiceRepository.deletePracticeData()
    }
}