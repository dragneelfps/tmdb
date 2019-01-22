package com.nooblabs.tmdb.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class CustomTypeConverters {

    val moshi = Moshi.Builder().build()
    val stringListType = Types.newParameterizedType(List::class.java, String::class.java)
    val stringListAdapter = moshi.adapter<List<String>>(stringListType)

    @TypeConverter
    fun stringToList(json: String?): List<String>? {
        return json?.let {
            stringListAdapter.fromJson(it)
        }
    }

    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return list?.let {
            stringListAdapter.toJson(it)
        }
    }

}