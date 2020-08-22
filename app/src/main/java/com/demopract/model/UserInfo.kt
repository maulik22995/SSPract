package com.demopract.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "UserInfo")
data class UserInfo(
    val cell: String,
    @PrimaryKey
    val email: String,
    val gender: String,
    val nat: String,
    val phone: String,
    val picture: Picture
)