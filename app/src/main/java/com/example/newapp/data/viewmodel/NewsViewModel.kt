package com.example.newapp.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.data.model.BaseApiResponse
import com.example.newapp.data.model.NewModel
import com.example.newapp.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository):ViewModel() {

    fun getNewsSearch(name:String, apikey:String){
        viewModelScope.launch {
            newsRepository.getNews(name,apikey)
        }
    }

    val news:LiveData<BaseApiResponse<NewModel>> get() = newsRepository.news


}