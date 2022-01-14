package es.jveron.practica101.data.repository

import es.jveron.practica101.domain.PracticeData
import es.jveron.practica101.domain.PracticeRepository

class PracticeDataRepositoryImpl : PracticeRepository {
    override fun getPracticeData(): PracticeData {
        return PracticeData("Práctica 03")
    }
    override fun addPracticeData(practiceData: PracticeData) {
    }
    override fun deletePracticeData() {
    }
    override fun updatePracticeData(practiceData: PracticeData) {
    }
}