package com.example.easysoccer1.ui.homeAdmin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeAdminViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragmentñ"
    }
    val text: LiveData<String> = _text
}