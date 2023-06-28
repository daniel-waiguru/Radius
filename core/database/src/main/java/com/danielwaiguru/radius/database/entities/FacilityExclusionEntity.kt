package com.danielwaiguru.radius.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity.Companion.FACILITIES_EXCLUSION_TABLE_NAME

@Entity(
    tableName = FACILITIES_EXCLUSION_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = FacilityEntity::class,
            childColumns = ["facility_id"],
            parentColumns = ["facility_id"],
            onDelete = CASCADE
        )
    ]
)
data class FacilityExclusionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("facility_id")
    val facilityId: String,
    @ColumnInfo("options_id")
    val optionsId: String
) {
    companion object {
        const val FACILITIES_EXCLUSION_TABLE_NAME = "facility_exclusions"
    }
}
