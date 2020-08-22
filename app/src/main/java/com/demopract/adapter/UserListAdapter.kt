package com.demopract.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demopract.R
import com.demopract.model.UserInfo
import kotlinx.android.synthetic.main.item_user_list.view.*


class UserListAdapter(context: Context, userList: List<UserInfo>) :
    RecyclerView.Adapter<UserListAdapter.UserInfoViewHolder>() {

    private val context = context
    private val userList = userList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false)
        return UserInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserInfoViewHolder, position: Int) {
        val user = userList[position]
        holder.itemView.tvEmail.text = user.email.toString()
        holder.itemView.tvGender.text = user.gender.toString()
        holder.itemView.tvhone.text = user.phone.toString()
        holder.itemView.ivProfile.setImageURI(user.picture.thumbnail)

    }

    public fun updateUserListItems(userData: List<UserInfo>) {
        /* val diffCallback = UserDataDiffCallback(this.userList, userData)
         val diffResult = DiffUtil.calculateDiff(diffCallback)
         this.userList.clear()
         this.userList.addAll(userData)
         diffResult.dispatchUpdatesTo(this)*/
    }

    class UserInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}