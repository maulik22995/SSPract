package com.demopract.network

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED

}

class NetWorkState(val state: Status, val msg: String?) {

    companion object {
        val LOADED: NetWorkState
        val LOADING: NetWorkState
        val ERROR: NetWorkState
        val ENDOFLIST: NetWorkState

        init {
            LOADED = NetWorkState(Status.SUCCESS, "Success")
            LOADING = NetWorkState(Status.RUNNING, "Running")

            ERROR = NetWorkState(Status.FAILED, "Something went wrong")

            ENDOFLIST = NetWorkState(Status.FAILED, "You have reached the end")
        }
    }
}