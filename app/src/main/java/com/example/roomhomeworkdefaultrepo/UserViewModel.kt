package com.example.roomhomeworkdefaultrepo

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class UserViewModel(application: Application, private val repository: UserRepository) : AndroidViewModel(application) {

    private val items = repository.getAll()

    fun insert(user: User){
        repository.insert(user)
    }

    fun delete(user: User){
        repository.delete(user)
    }

    fun update(user : User){
        repository.update(user)
    }

    fun getAll(): List<User> {
        return items
    }

}