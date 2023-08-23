package com.example.newapp.data.model

data class NewModelResponse(
    val source: Source,
    val author:String,
    val title:String,
    val description:String,
    val urlToImage:String
) {
}