package com.demopract.network


import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetroClient {


    private var retstApi: RestApi? = null
    private val SINGLE_UPLOAD_TIME_OUT = 1000


    fun getClient(BASE_URL: String): RestApi? {
        try {
            if (retstApi == null) {
                val gson = Gson()
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)


                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(30000, TimeUnit.SECONDS)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(30000, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build()


                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ToStringConverter())
                    .client(okHttpClient)
                    .build()


                retstApi = retrofit.create(RestApi::class.java)

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return retstApi
    }

}
