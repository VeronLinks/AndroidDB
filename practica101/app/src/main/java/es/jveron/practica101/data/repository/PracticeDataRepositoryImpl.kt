package es.jveron.practica101.data.repository

import es.jveron.practica101.domain.PracticeData
import es.jveron.practica101.domain.PracticeRepository

class PracticeDataRepositoryImpl(private val practiceDataSharedPref:
                                 PracticeDataSharedPreferences) : PracticeRepository {
    override fun getPracticeData(): PracticeData {
        val name = practiceDataSharedPref.getPrefData()
        return PracticeData(name)
    }
    override fun addPracticeData(practiceData: PracticeData) {
        practiceDataSharedPref.addPrefData(practiceData.name)
    }
    override fun deletePracticeData() {
        practiceDataSharedPref.deletePrefData()
    }
    override fun updatePracticeData(practiceData: PracticeData) {
        practiceDataSharedPref.updatePrefData(practiceData.name)
    }
}