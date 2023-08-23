package com.example.newapp.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.data.model.NewModel
import kotlinx.coroutines.launch

class NewSharedViewModel :ViewModel() {

     private val _newModelLiveData = MutableLiveData<NewModel>()
    val new :LiveData<NewModel> get() = _newModelLiveData

    fun setNewModel(newModel: NewModel){
        viewModelScope.launch {
            _newModelLiveData.value=newModel
        }

    }


}