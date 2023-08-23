package com.example.newapp.data.network

import com.example.newapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    @Provides
    @Singleton
    fun getIntance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun NewApiService(retrofit: Retrofit): NewApi {
        return retrofit.create(NewApi::class.java)
    }

}