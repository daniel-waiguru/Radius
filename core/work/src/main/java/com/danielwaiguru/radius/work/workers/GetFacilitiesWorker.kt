package com.danielwaiguru.radius.work.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.danielwaiguru.radius.common.utils.Dispatcher
import com.danielwaiguru.radius.common.utils.RadiusDispatchers.IO
import com.danielwaiguru.radius.database.data_source.RadiusLocalDataSource
import com.danielwaiguru.radius.facilities_data.api.data_source.RadiusNetworkDataSource
import com.danielwaiguru.radius.facilities_data.mappers.toFacilityEntity
import com.danielwaiguru.radius.facilities_data.mappers.toFacilityExclusionEntity
import com.danielwaiguru.radius.facilities_data.models.data.network.NetworkFacility
import com.danielwaiguru.radius.facilities_data.models.data.network.NetworkFacilityExclusion
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@HiltWorker
internal class GetFacilitiesWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val radiusNetworkDataSource: RadiusNetworkDataSource,
    private val radiusLocalDataSource: RadiusLocalDataSource,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result= withContext(ioDispatcher) {
        try {
            val response = radiusNetworkDataSource.getFacilities()
            if (response.isSuccessful.not() && response.body() == null) {
                return@withContext Result.retry()
            }
            val facilities = response.body()!!.facilities
            radiusLocalDataSource.upsertFacilities(
                facilities.map(NetworkFacility::toFacilityEntity)
            )
            val facilitiesExclusion = response.body()!!.exclusions.flatten()
            radiusLocalDataSource.upsertFacilitiesExclusion(
                facilitiesExclusion.map(NetworkFacilityExclusion::toFacilityExclusionEntity)
            )

            Result.success()
        } catch (exception: Exception) {
            exception.printStackTrace()
            //print to logcat or log to crashlytics and return retry
            Result.retry()
        }

    }
    companion object {
        val uniqueWorkName: String = GetFacilitiesWorker::class.java.simpleName
    }
}