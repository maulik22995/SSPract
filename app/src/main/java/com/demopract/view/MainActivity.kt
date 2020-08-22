package com.demopract.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demopract.R
import com.demopract.adapter.UserListAdapter
import com.demopract.model.UserInfo
import com.demopract.network.NetWorkState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var userAdapter: UserListAdapter? = null

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
                .create(MainActivityViewModel::class.java)
        mainActivityViewModel.userList.observe(this, Observer {
            setUpList(it)
        })

        mainActivityViewModel?.networkState?.observe(this, Observer {
            if (it == NetWorkState.LOADING && mainActivityViewModel.listIsEmpty()) {
                loader?.visibility = View.VISIBLE
                tvNoData.visibility = View.GONE
            } else {
                loader?.visibility = View.GONE
            }

        })
    }

    private fun setUpList(data: List<UserInfo>) {
        userAdapter = UserListAdapter(this, data)
        reyData.adapter = userAdapter
//        userAdapter!!.updateUserListItems(data)
        if (data.size > 0) {
            loader.visibility = View.GONE
            tvNoData.visibility = View.GONE
        } else {
            tvNoData.visibility = View.VISIBLE
        }
    }
}
