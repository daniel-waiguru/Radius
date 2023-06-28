package com.danielwaiguru.radius.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielwaiguru.radius.database.daos.FacilityDao
import com.danielwaiguru.radius.database.daos.FacilityExclusionDao
import com.danielwaiguru.radius.database.entities.FacilityEntity
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity

@Database(
    entities = [
        FacilityEntity::class,
    FacilityExclusionEntity::class
               ],
    version = 1,
    exportSchema = true
)
internal abstract class RadiusDatabase: RoomDatabase() {
    internal abstract fun facilityDao(): FacilityDao
    internal abstract fun facilityExclusionDao(): FacilityExclusionDao
    companion object {
        const val DB_NAME = "radius_db"
    }
}