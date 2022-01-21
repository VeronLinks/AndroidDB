package es.jveron.cities.data.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert
    suspend fun insertCity(cityDbModel: CityDbModel)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getCities(): Flow<List<CityDbModel>>

    @Insert
    suspend fun insertSight(sightDbModel: SightDbModel)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :cityId")
    suspend fun getCityAndSights(cityId: Int): CityAndSightsDbModel

}