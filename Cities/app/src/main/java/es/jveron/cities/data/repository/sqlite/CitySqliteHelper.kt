package es.jveron.cities.data.repository.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_DESCRIPTION
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_NAME
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.COLUMN_SUNSHINE_HOURS
import es.jveron.cities.data.repository.sqlite.CityContract.CityEntity.TABLE_NAME

const val CITIES_DATABASE = "CitiesDatabase.db"
const val CITIES_DATABASE_VERSION = 1

object CityContract {
    object CityEntity : BaseColumns {
        val COLUMN_NAME = "name"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_SUNSHINE_HOURS = "sunshine_hours"
        const val TABLE_NAME = "cities"
    }
}

val CREATE_STATEMENT = "CREATE TABLE $TABLE_NAME ( " +
        "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
        "${COLUMN_NAME} TEXT," +
        "${COLUMN_DESCRIPTION} TEXT," +
        "${COLUMN_SUNSHINE_HOURS} INTEGER" +
        ")"

val DELETE_STATEMENT = "DROP TABLE IF EXISTS $TABLE_NAME"

class CitySqliteHelper(context: Context): SQLiteOpenHelper(context, CITIES_DATABASE, null, CITIES_DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_STATEMENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DELETE_STATEMENT)
        onCreate(db)
    }

}