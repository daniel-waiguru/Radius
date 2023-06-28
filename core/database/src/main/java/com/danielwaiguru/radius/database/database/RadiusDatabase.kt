package com.danielwaiguru.radius.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielwaiguru.radius.database.daos.FacilityDao
import com.danielwaiguru.radius.database.daos.FacilityExclusionDao

@Database(
    entities = [],
    version = 1,
    exportSchema = true
)
abstract class RadiusDatabase: RoomDatabase() {
    abstract fun facilityDao(): FacilityDao
    abstract fun facilityExclusionDao(): FacilityExclusionDao
    companion object {
        const val DB_NAME = "radius_db"
    }
}