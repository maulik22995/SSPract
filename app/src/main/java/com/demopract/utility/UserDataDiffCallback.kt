package com.demopract.utility

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.demopract.model.UserInfo


class UserDataDiffCallback(
    OldUserList: List<UserInfo>,
     NewUserList: List<UserInfo>
) :
    DiffUtil.Callback() {

    private var mOldUserList: List<UserInfo>? = null
    private var mNewUserList: List<UserInfo>? = null

    override fun getOldListSize(): Int {
        return mOldUserList!!.size
    }

    override fun getNewListSize(): Int {
        return mNewUserList!!.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return mOldUserList!![oldItemPosition].email.equals(mNewUserList!![newItemPosition].email)
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldUser: UserInfo = mOldUserList!![oldItemPosition]
        val newUser: UserInfo = mNewUserList!![newItemPosition]
        return oldUser.email.equals(newUser.email)
    }

    @Nullable
    override fun getChangePayload(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Any? { // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

    init {
        mOldUserList = OldUserList
        mNewUserList = NewUserList
    }
}