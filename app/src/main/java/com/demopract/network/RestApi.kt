package com.demopract.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("/api")
    suspend fun getAllUserList(@Query("results") results : Int) : Response<String>
}