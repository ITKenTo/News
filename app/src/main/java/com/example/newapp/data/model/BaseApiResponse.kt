package com.example.newapp.data.model

data class BaseApiResponse<T> (
    val status:String,
    val totalResults:Int,
    val articles:List<T>
        )  {
}