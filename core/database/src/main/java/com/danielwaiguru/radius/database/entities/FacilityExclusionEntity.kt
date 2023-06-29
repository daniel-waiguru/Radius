package com.danielwaiguru.radius.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.danielwaiguru.radius.database.converters.FacilityExclusionConverter
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity.Companion.FACILITIES_EXCLUSION_TABLE_NAME

@Entity(
    tableName = FACILITIES_EXCLUSION_TABLE_NAME
)
@TypeConverters(
    FacilityExclusionConverter::class
)
data class FacilityExclusionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val exclusions: List<List<ExclusionEntity>>
) {
    data class ExclusionEntity(
        @ColumnInfo("facility_id")
        val facilityId: String,
        @ColumnInfo("options_id")
        val optionsId: String
    )
    companion object {
        const val FACILITIES_EXCLUSION_TABLE_NAME = "facility_exclusions"
    }
}
