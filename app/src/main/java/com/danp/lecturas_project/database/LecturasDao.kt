package com.danp.lecturas_project.database

import androidx.room.*


@Dao
interface LecturasDao {

    @Query("SELECT id FROM LecturasEntity WHERE usuario = :user")
    suspend fun getIds(user: String): List<Int>

    @Query("SELECT * FROM LecturasEntity")
    suspend fun getAll(): List<LecturasEntity>

    @Query("SELECT * FROM LecturasEntity WHERE usuario = :user")
    suspend fun getByUser(user: String): List<LecturasEntity>

    /*@Query("SELECT * FROM LecturasEntity WHERE usuario = :user ORDER BY tiempo DESC LIMIT 1")
    fun getUltimo(user: String): LecturasEntity*/
    @Query("SELECT * FROM LecturasEntity WHERE usuario = :user ORDER BY orden DESC LIMIT 1")
    suspend fun getUltimo(user: String): LecturasEntity

    @Query("SELECT COUNT(*) FROM LecturasEntity")
    suspend fun getCantidad(): Int

    @Query("SELECT COUNT(*) FROM LecturasEntity WHERE usuario = :user")
    suspend fun getOrden(user: String): Int

    @Insert
    suspend fun insert(lectura: LecturasEntity)

    @Update
    suspend fun update(lectura: LecturasEntity)

    @Delete
    suspend fun delete(lectura: LecturasEntity)
}