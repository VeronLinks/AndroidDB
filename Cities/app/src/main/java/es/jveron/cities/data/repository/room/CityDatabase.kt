package es.jveron.cities.data.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityDbModel::class, SightDbModel::class], version = 1, exportSchema = true)
abstract class CityDatabase : RoomDatabase() {

    abstract fun getDao(): CityDao

    companion object {

        @Volatile
        private var INSTANCE : CityDatabase? = null

        fun getDatabase(context: Context): CityDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CityDatabase::class.java,
                    "CitiesRoom.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}