package com.example.newapp.data.network

import com.example.newapp.data.model.BaseApiResponse
import com.example.newapp.data.model.NewModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApi {

    @GET("everything")
    suspend fun getNewApi(
        @Query("q") name:String,
        @Query("apikey") apikey:String

    ):Response<BaseApiResponse<NewModel>>
}