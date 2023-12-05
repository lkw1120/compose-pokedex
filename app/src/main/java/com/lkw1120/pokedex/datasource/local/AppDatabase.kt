package com.lkw1120.pokedex.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lkw1120.pokedex.common.Constants.DATABASE_NAME
import com.lkw1120.pokedex.datasource.entity.Converters
import com.lkw1120.pokedex.datasource.entity.DetailEntity
import com.lkw1120.pokedex.datasource.entity.PokeEntity
import com.lkw1120.pokedex.datasource.local.dao.DetailDao
import com.lkw1120.pokedex.datasource.local.dao.PokeDao

@Database(
    entities = [
        PokeEntity::class,
        DetailEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokeDao(): PokeDao
    abstract fun DetailDao(): DetailDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}