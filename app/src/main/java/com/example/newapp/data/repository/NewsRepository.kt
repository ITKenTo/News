package com.example.newapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newapp.data.network.NewApi
import com.example.newapp.data.model.BaseApiResponse
import com.example.newapp.data.model.NewModel

class NewsRepository(private val api: NewApi) {

    private val _newLiveData= MutableLiveData<BaseApiResponse<NewModel>>()

    val news:LiveData<BaseApiResponse<NewModel>> get() = _newLiveData

    suspend fun getNews(name:String,apikey:String){
        val result = api.getNewApi(name,apikey)
        if (result.body()!=null){
            _newLiveData.postValue(result.body())
        }
    }
}