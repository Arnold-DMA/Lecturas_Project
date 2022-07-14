package com.danp.lecturas_project.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [LecturasEntity::class],
    version = 1
)
abstract class LecturasDataBase : RoomDatabase() {

    abstract fun lecturaDao(): LecturasDao
    //abstract fun rankingDao(): RankingDao
}