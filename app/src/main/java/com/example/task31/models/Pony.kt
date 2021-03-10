package com.example.task31.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "pony")
data class Pony(
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "tailLength") val tailLength: String,
    @ColumnInfo(name = "sex") val sex: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

class PonyRaceGenerator{
    fun generate(minPonyCount:Int,maxPonyCount:Int):List<Pony> {
        val ponyRace:MutableList<Pony> = mutableListOf()
        for(i in minPonyCount..maxPonyCount){
            ponyRace.add(
                Pony(
                    Random.nextInt(1,1000).toString(),
                    Random.nextInt(1,1000).toString(),
                    Random.nextInt(1,2)
                ))
        }
        return ponyRace.toList()
    }
}