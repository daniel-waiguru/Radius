package com.danielwaiguru.radius.database.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.danielwaiguru.radius.database.entities.FacilityEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

class FacilityOptionsConverter: Serializable {
    private val gson: Gson by lazy { Gson() }
    private val type: Type by lazy {
        object : TypeToken<List<FacilityEntity.OptionEntity>>() {}.type
    }
    @TypeConverter
    fun fromListToString(options: List<FacilityEntity.OptionEntity>): String? {
        return gson.toJson(options, type)
    }

    @TypeConverter
    fun fromStringToList(jsonString: String?): List<FacilityEntity.OptionEntity>? {
        return gson.fromJson(jsonString, type)
    }
}