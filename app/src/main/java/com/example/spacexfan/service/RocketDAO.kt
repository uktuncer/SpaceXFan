package com.example.spacexfan.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.spacexfan.model.Rocket

@Dao
interface RocketDAO {

    @Insert

    suspend fun insertAll(vararg Rockets:Rocket):List<Long>

    @Query("SELECT * FROM rocket")

    suspend fun getAllRockets():List<Rocket>

    @Query("SELECT * FROM rocket WHERE uuid = :rocketId")
    suspend fun getRockets(rocketId : Int):Rocket

    @Query("DELETE FROM rocket")
    suspend fun deleteAllRockets()
}

