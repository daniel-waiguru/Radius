package com.danielwaiguru.radius.work

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.danielwaiguru.radius.work.workers.GetFacilitiesWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface RadiusWorkScheduler {
    fun scheduleGetFacilities()
}

internal class RadiusWorkSchedulerImpl @Inject constructor(
    private val workManager: WorkManager
): RadiusWorkScheduler {
    private val workConstrains: Constraints by lazy {
        Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }
    private fun isWorkScheduled(workName: String): LiveData<Boolean> =
        workManager.getWorkInfosForUniqueWorkLiveData(workName).map {  workInfos ->
            workInfos.isNotEmpty() && workInfos.getOrNull(0)
                ?.state != WorkInfo.State.CANCELLED
        }

    override fun scheduleGetFacilities() {
        val isWorkScheduled = isWorkScheduled(GetFacilitiesWorker.uniqueWorkName).value ?: false
        /**
         * check if work is scheduled and return early
         */
        if (isWorkScheduled) return
        // refresh local cache once a day periodically
        val workRequest = PeriodicWorkRequestBuilder<GetFacilitiesWorker>(
            1,
            TimeUnit.DAYS
        )
            .setConstraints(workConstrains)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                0,
                TimeUnit.MILLISECONDS
            )
            .build()
        // enqueueUniquePeriodicWork to ignore consecutive works when there is an ongoing work
        workManager.enqueueUniquePeriodicWork(
            GetFacilitiesWorker.uniqueWorkName,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}