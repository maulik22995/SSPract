package com.demopract.view

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demopract.BuildConfig
import com.demopract.MyApplicationClass
import com.demopract.model.UserInfo
import com.demopract.network.NetWorkState
import com.demopract.network.RetroClient
import com.demopract.utility.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import java.util.ArrayList
import java.util.logging.Logger
import kotlin.coroutines.CoroutineContext

class MainActivityRepo(val application: Application) : CoroutineScope {

    val netWorkState: LiveData<NetWorkState> get() = _networkState
    private var _networkState: MutableLiveData<NetWorkState> = MutableLiveData()
    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getUserList() : LiveData<List<UserInfo>>{
        if(Utils.isNetworkConnected(application)){
            getFromApiAndStorInDb()
        }
        return MyApplicationClass.database!!.userInfoDao().getAllUserInfo()
    }


    fun getFromApiAndStorInDb(){
        job = Job()
        _networkState.postValue(NetWorkState.LOADING)

        launch {
            val response: Response<String> =
                RetroClient.getClient(BuildConfig.BASE_URL)!!.getAllUserList(100)

            if (response.body() != null) {
                Log.e("onResp>>",response.body())
                val jsonObject = JSONObject(response.body().toString())
                val results = jsonObject.getJSONArray("results")
                val userList = Gson().fromJson<ArrayList<UserInfo>>(
                    results.toString(),
                    object : TypeToken<ArrayList<UserInfo>>() {
                    }.type
                )

                Thread(Runnable {

                    MyApplicationClass.database!!.userInfoDao().deleteAllUserInfo()
                    MyApplicationClass.database!!.userInfoDao().insertAllUserInfo(userList!!)

                }).start()
                _networkState.postValue(NetWorkState.LOADED)
            } else {

                _networkState.postValue(NetWorkState.ERROR)

            }

        }
    }
}