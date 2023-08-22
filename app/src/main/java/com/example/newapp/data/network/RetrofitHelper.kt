package com.example.newapp.data.network

import com.example.newapp.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getIntance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}