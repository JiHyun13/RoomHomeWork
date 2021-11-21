package com.example.roomhomeworkdefaultrepo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey
        var name : String,
        var birthday : String,
        var email : String
)
