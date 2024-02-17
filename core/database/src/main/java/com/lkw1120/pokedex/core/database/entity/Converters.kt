package com.lkw1120.pokedex.core.database.entity

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    @TypeConverter
    fun statsToJson(stats: List<StatEntity>): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val data = Types.newParameterizedType(
            MutableList::class.java,
            StatEntity::class.java
        )
        val adapter = moshi.adapter<List<StatEntity>>(data)
        return adapter.toJson(stats)
    }

    @TypeConverter
    fun jsonToStats(json: String): List<StatEntity> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val data = Types.newParameterizedType(
            MutableList::class.java,
            StatEntity::class.java
        )
        val adapter = moshi.adapter<List<StatEntity>>(data)
        return adapter.fromJson(json)?:listOf()
    }

    @TypeConverter
    fun typesToJson(stats: List<TypeEntity>): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val data = Types.newParameterizedType(
            MutableList::class.java,
            TypeEntity::class.java
        )
        val adapter = moshi.adapter<List<TypeEntity>>(data)
        return adapter.toJson(stats)
    }

    @TypeConverter
    fun jsonToTypes(json: String): List<TypeEntity> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val data = Types.newParameterizedType(
            MutableList::class.java,
            TypeEntity::class.java
        )
        val adapter = moshi.adapter<List<TypeEntity>>(data)
        return adapter.fromJson(json)?:listOf()
    }
}