package com.kylechen2149.interviewsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    var isShowToolbar = MutableLiveData(false)
    val toolbarTitle = MutableLiveData("test")
    val toolbarBack = MutableLiveData(false)
    val isShowToolbarTitle = MutableLiveData(false)
    val isShowTimeZone = MutableLiveData(false)
    val isShowBack = MutableLiveData(false)
    val timeZoneClick = MutableLiveData(false)
    fun onToolbarBackClick() = viewModelScope.launch {
        toolbarBack.postValue(true)
    }

    fun onTimeZoneClick() = viewModelScope.launch {
        timeZoneClick.postValue(true)
    }
}