package com.example.task31.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.task31.models.Pony

@Dao
interface PonyDao {
    @Insert
    fun insert(pony: List<Pony>)

    @Query("SELECT * FROM pony")
    fun getPonies() : List<Pony>

    @Query  ("DELETE FROM pony")
    fun delete()
}