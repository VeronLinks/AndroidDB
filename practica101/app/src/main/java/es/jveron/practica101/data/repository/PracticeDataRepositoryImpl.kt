package es.jveron.practica101.data.repository

import es.jveron.practica101.domain.PracticeData
import es.jveron.practica101.domain.PracticeRepository

class PracticeDataRepositoryImpl(private val dataStore: PracticeDataStore) :
    PracticeRepository {
    override fun getPracticeData(): Flow<PracticeData> {
        return dataStore.getDataStoreData().map { practiceName ->
            PracticeData(practiceName)
        }
    }
    override suspend fun addPracticeData(practiceData: PracticeData) {
        dataStore.addDataStoreData(practiceData.name)
    }
    override suspend fun deletePracticeData() {
        dataStore.deleteDataStoreData()
    }
    override suspend fun updatePracticeData(practiceData: PracticeData) {
        dataStore.updateDataStoreData(practiceData.name)
    }
}
