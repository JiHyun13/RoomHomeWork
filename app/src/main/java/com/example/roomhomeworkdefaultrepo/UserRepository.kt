package com.example.roomhomeworkdefaultrepo

import android.content.Context


class UserRepository(private val userDat : Dao, context: Context) {
    private val userDao : Dao
    private val userList : List<User>

    init {
        var db = AppDataBase.getInstance(context)
        userDao = db!!.userDao()
        userList = db.userDao().getAll()
    }

    fun insert(user: User){
        userDao.insertData(user)
    }

    fun delete(user: User){
        userDao.deleteData(user)
    }

    fun update(user : User){
        userDao.updateData(user)
    }

    fun getAll(): List<User> {
        return userDao.getAll()
    }
}