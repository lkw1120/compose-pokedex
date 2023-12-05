package com.lkw1120.pokedex.datasource.entity

import androidx.room.TypeConverter
import com.lkw1120.pokedex.usecase.model.StatInfo
import com.lkw1120.pokedex.usecase.model.TypeInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    @TypeConverter
    fun statsToJson(stats: List<StatInfo>): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val data = Types.newParameterizedType(
            MutableList::class.java,
            StatInfo::class.java
        )
        val adapter = moshi.adapter<List<StatInfo>>(data)
        return adapter.toJson(stats)
    }

    @TypeConverter
    fun jsonToStats(json: String): List<StatInfo> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val data = Types.newParameterizedType(
            MutableList::class.java,
            StatInfo::class.java
        )
        val adapter = moshi.adapter<List<StatInfo>>(data)
        return adapter.fromJson(json)?:listOf()
    }

    @TypeConverter
    fun typesToJson(stats: List<TypeInfo>): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val data = Types.newParameterizedType(
            MutableList::class.java,
            TypeInfo::class.java
        )
        val adapter = moshi.adapter<List<TypeInfo>>(data)
        return adapter.toJson(stats)
    }

    @TypeConverter
    fun jsonToTypes(json: String): List<TypeInfo> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val data = Types.newParameterizedType(
            MutableList::class.java,
            TypeInfo::class.java
        )
        val adapter = moshi.adapter<List<TypeInfo>>(data)
        return adapter.fromJson(json)?:listOf()
    }
}