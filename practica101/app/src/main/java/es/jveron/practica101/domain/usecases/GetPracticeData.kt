package es.jveron.practica101.domain.usecases

import es.jveron.practica101.domain.PracticeData
import es.jveron.practica101.domain.PracticeRepository
import kotlinx.coroutines.flow.Flow

class GetPracticeData(private val practiceRepository: PracticeRepository) {
    fun getPracticeData(): Flow<PracticeData> {
        return practiceRepository.getPracticeData()
    }
}