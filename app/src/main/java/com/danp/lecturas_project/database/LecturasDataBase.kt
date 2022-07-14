package com.danp.lecturas_project.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.newCoroutineContext
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.coroutineContext


@Database(
    entities = [LecturasEntity::class],
    exportSchema = false,
    version = 2
)

abstract class LecturasDataBase : RoomDatabase() {

    abstract fun lecturaDao(): LecturasDao
    //abstract fun rankingDao(): RankingDao
}