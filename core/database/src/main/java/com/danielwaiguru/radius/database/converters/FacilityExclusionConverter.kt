package com.danielwaiguru.radius.database.converters

import androidx.room.TypeConverter
import com.danielwaiguru.radius.database.entities.FacilityExclusionEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

class FacilityExclusionConverter: Serializable {
    private val gson: Gson by lazy { Gson() }
    private val type: Type by lazy {
        object : TypeToken<List<List<FacilityExclusionEntity.ExclusionEntity>>>() {}.type
    }
    @TypeConverter
    fun fromListToString(options: List<List<FacilityExclusionEntity.ExclusionEntity>>): String? {
        return gson.toJson(options, type)
    }

    @TypeConverter
    fun fromStringToList(jsonString: String?): List<List<FacilityExclusionEntity.ExclusionEntity>>? {
        return gson.fromJson(jsonString, type)
    }
}