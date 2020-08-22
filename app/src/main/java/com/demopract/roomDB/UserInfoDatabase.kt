package com.demopract.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.demopract.model.PictureConverters
import com.demopract.model.UserInfo

@Database(entities = [(UserInfo::class)],version = 1)
@TypeConverters(PictureConverters::class)
abstract class UserInfoDatabase : RoomDatabase(){
    abstract fun userInfoDao() : UserInfoDao
}