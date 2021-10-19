package com.example.sampleclassifiedsapp.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    val Base_URL_API = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/"

    private var retrofit: Retrofit? = null
    var okHttpClient = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.SECONDS)
        .connectTimeout(5000, TimeUnit.SECONDS)
        .build()

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Base_URL_API)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .build()
        }
        return retrofit!!
    }

}