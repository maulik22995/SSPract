package com.demopract.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demopract.model.UserInfo

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM userinfo")
    fun getAllUserInfo() : LiveData<List<UserInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUserInfo(countryList: List<UserInfo>)

    @Query("DELETE FROM userinfo")
    fun deleteAllUserInfo()
}