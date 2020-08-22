package com.demopract.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demopract.model.UserInfo
import com.demopract.network.NetWorkState

class MainActivityViewModel(application: Application) : AndroidViewModel(application){
    lateinit var mainActivityRepo: MainActivityRepo
    val networkState: LiveData<NetWorkState> by lazy {
        mainActivityRepo.netWorkState
    }
    init {
        mainActivityRepo = MainActivityRepo(application)
    }

    val userList : LiveData<List<UserInfo>> by lazy{
        mainActivityRepo.getUserList()
    }


    fun listIsEmpty(): Boolean {
        return userList.value?.isEmpty() ?: true
    }
}