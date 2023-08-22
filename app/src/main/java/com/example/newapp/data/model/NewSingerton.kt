package com.example.newapp.data.model

class NewSingerton private constructor(){

    var new: NewModel?=null

    companion object{
        @Volatile
        private var instance: NewSingerton? = null

        fun getInstance(): NewSingerton {
            return instance ?: synchronized(this) {
                instance ?: NewSingerton().also { instance = it }
            }
        }
    }
}