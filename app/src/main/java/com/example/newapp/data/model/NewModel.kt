package com.example.newapp.data.model

import java.io.Serializable

data class NewModel(
    val source: Source,
    val author:String,
    val title:String,
    val description:String,
    val urlToImage:String
):Serializable