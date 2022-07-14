package com.danp.lecturas_project.pager

import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danp.lecturas_project.MainActivity.Companion.db

import com.danp.lecturas_project.database.LecturasDataBase
import com.danp.lecturas_project.database.LecturasEntity
import kotlinx.coroutines.delay

class Repository /*constructor(val dbl: LecturasDataBase)*/ {

    //private val remoteDataSource = db.lecturaDao().getAll()
    lateinit var remoteDataSource: List<LecturasEntity>
    /*private val remoteDataSource = (1..100).map {
        ListItem(
            title = "Item $it",
            description = "Description $it"
        )
    }*/

    suspend fun getItems(page: Int, pageSize: Int): Result<List<LecturasEntity>> {
        remoteDataSource = db.lecturaDao().getPuntajes()
        delay(2000L)
        val startingIndex = page * pageSize
        return if(startingIndex + pageSize <= remoteDataSource.size) {
            Result.success(
                remoteDataSource.slice(startingIndex until startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}