package com.example.roomhomeworkdefaultrepo

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Query("SELECT * FROM User")
    fun getAll() : List<User>

    @Query("SELECT name FROM User")
    fun getAllName() : List<String>

    @Query("SELECT birthday FROM User")
    fun getAllBirthday() : List<String>

    @Query("SELECT email FROM User")
    fun getAllEmail() : List<String>

    @Insert
    fun insertData(vararg user : User)

    @Delete
    fun deleteData(user : User)

    @Update
    fun updateData(user: User)
}