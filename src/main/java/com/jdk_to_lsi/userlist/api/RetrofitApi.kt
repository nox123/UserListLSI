package com.jdk_to_lsi.userlist.api

import com.google.gson.GsonBuilder
import com.jdk_to_lsi.userlist.model.DailymotionData
import com.jdk_to_lsi.userlist.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitApi {
    @GET("/users")
    suspend fun getDataFromServer(): Response<List<User>>
    @GET("/users")
    suspend fun getDataFromServer2(): Response<DailymotionData>
    companion object {
        fun create(baseUrl:String): RetrofitApi {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RetrofitApi::class.java)

        }
    }
}