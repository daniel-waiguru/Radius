package com.danielwaiguru.radius.database.di

import android.content.Context
import androidx.room.Room
import com.danielwaiguru.radius.database.daos.FacilityDao
import com.danielwaiguru.radius.database.daos.FacilityExclusionDao
import com.danielwaiguru.radius.database.database.RadiusDatabase
import com.danielwaiguru.radius.database.database.RadiusDatabase.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PersistenceModule {
    @Provides
    @Singleton
    internal fun providesRadiusDatabase(
        @ApplicationContext appContext: Context
    ): RadiusDatabase = Room.databaseBuilder(
        appContext,
        RadiusDatabase::class.java,
        DB_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    internal fun providesFacilityDao(
        radiusDatabase: RadiusDatabase
    ): FacilityDao = radiusDatabase.facilityDao()

    @Provides
    internal fun providesFacilityExclusionDao(
        radiusDatabase: RadiusDatabase
    ): FacilityExclusionDao = radiusDatabase.facilityExclusionDao()
}