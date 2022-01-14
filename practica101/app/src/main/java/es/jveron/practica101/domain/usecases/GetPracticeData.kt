package es.jveron.practica101.domain.usecases

import es.jveron.practica101.domain.PracticeData
import es.jveron.practica101.domain.PracticeRepository

class GetPracticeData(private val practiceRepository: PracticeRepository) {
    fun getPracticeData(): PracticeData {
        return practiceRepository.getPracticeData()
    }
}