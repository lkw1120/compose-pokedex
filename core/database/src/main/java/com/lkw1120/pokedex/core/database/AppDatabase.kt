package com.lkw1120.pokedex.core.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lkw1120.pokedex.core.common.Constants.DATABASE_NAME
import com.lkw1120.pokedex.core.database.dao.DetailDao
import com.lkw1120.pokedex.core.database.dao.PokeDao
import com.lkw1120.pokedex.core.database.entity.Converters
import com.lkw1120.pokedex.core.database.entity.DetailEntity
import com.lkw1120.pokedex.core.database.entity.PokeEntity

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
            application: Application
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        application,
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