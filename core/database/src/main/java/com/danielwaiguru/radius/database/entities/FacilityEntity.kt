package com.danielwaiguru.radius.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.danielwaiguru.radius.database.converters.FacilityOptionsConverter
import com.danielwaiguru.radius.database.entities.FacilityEntity.Companion.FACILITIES_TABLE_NAME

@Entity(tableName = FACILITIES_TABLE_NAME)
@TypeConverters(
    FacilityOptionsConverter::class
)
data class FacilityEntity(
    @PrimaryKey
    @ColumnInfo(name = "facility_id")
    val facilityId: String,
    val name: String,
    val options: List<OptionEntity>
) {
    data class OptionEntity(
        val icon: String,
        val id: String,
        val name: String
    )
    companion object {
        const val FACILITIES_TABLE_NAME = "facilities"
    }
}
