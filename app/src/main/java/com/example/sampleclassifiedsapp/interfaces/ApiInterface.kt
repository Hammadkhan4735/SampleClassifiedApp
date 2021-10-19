package com.example.sampleclassifiedsapp.interfaces


import com.example.sampleclassifiedsapp.model.GetResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET


interface ApiInterface {

        @GET("default/dynamodb-writer")
        fun getDynamoWriters(): Call<GetResponseModel>

}