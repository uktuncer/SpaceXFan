package com.example.spacexfan.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.spacexfan.model.Converters
import com.example.spacexfan.model.Rocket


@Database(entities = arrayOf(Rocket::class),version = 1)
@TypeConverters(Converters::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao() : RocketDAO

    companion object{
       @Volatile private var instance: RocketDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance?: makeDataBase(context).also {
                instance = it
            }
        }

        private fun makeDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext , RocketDatabase::class.java ,"rocketdatabase"
        ).build()
    }
}