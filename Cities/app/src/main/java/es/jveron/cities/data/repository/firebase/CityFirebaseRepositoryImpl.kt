package es.jveron.cities.data.repository.firebase

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import es.jveron.cities.domain.model.City
import es.jveron.cities.domain.model.CityAndSights
import es.jveron.cities.domain.model.CityFilter
import es.jveron.cities.domain.model.Sight
import es.jveron.cities.domain.repository.CityRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import es.jveron.cities.data.repository.CityMapper
import es.jveron.cities.data.repository.SightMapper
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first


class CityFirebaseRepositoryImpl : CityRepository {

    val database = Firebase.database
    val cities = database.getReference("cities")
    val filters = database.getReference("filters")

    override suspend fun getCities(): Flow<List<City>> {
        return callbackFlow<List<City>> {

            cities.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val cityList = mutableListOf<CityFirebaseModel>()

                    for (snap in dataSnapshot.children){
                        val city = snap.getValue<CityFirebaseModel>() as CityFirebaseModel
                        cityList.add(city)
                    }

                    val mappedCities = cityList.map { city ->
                        CityMapper.mapCityFromFirebaseToDomain(city)
                    }

                    trySend(mappedCities)

                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })

            awaitClose()
        }
    }

    override suspend fun addCity(city: City) {
        cities.push().setValue(city)
    }

    override suspend fun setCityFilter(cityFilter: CityFilter) {
        filters.setValue(cityFilter)
    }

    override suspend fun getCityFilter(): Flow<CityFilter> {
        return callbackFlow<CityFilter> {

            filters.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val cityFilter = dataSnapshot.getValue<CityFilter>() as CityFilter

                    cityFilter?.let { trySend(it) }

                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })

            awaitClose()
        }
    }

    override suspend fun getCityAndSights(cityId: Int): CityAndSights {
        val flow = callbackFlow<CityAndSights> {

            cities.child(cityId.toString()).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val cityFirebaseModel = dataSnapshot.getValue<CityFirebaseModel>()

                    val sights = cityFirebaseModel?.sights?.map {
                        SightMapper.mapSightFromFirebaseToDomain(it)
                    } ?: arrayListOf()

                    cityFirebaseModel?.let {
                        val city = CityMapper.mapCityFromFirebaseToDomain(cityFirebaseModel)
                        val cityDetails = CityAndSights(city, sights)
                        trySend(cityDetails)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })

            awaitClose()
        }

        return flow.first()
    }

    override suspend fun addSight(sight: Sight) {
        cities.child(sight.id.toString()).push()
    }


}