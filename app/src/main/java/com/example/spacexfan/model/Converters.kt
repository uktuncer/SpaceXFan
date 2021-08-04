package com.example.spacexfan.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }

    @TypeConverter
    fun listToJson(value: List<Cores>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Cores>::class.java).toList()
}