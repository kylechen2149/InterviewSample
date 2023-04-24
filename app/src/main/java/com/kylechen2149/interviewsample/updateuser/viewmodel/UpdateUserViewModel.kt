package com.kylechen2149.interviewsample.updateuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylechen2149.interviewsample.updateuser.repository.UpdateUserRepository
import kotlinx.coroutines.launch

class UpdateUserViewModel(private val updateUserRepository: UpdateUserRepository): ViewModel() {

    val email = MutableLiveData("")
    val timeZone = MutableLiveData("")
    private val sessionToken = MutableLiveData("")
    private val objectId = MutableLiveData("")

    fun getUserData() = viewModelScope.launch {
        email.postValue(updateUserRepository.getUserName())
        timeZone.postValue(updateUserRepository.getTimeZone())
        sessionToken.postValue(updateUserRepository.getToken())
        objectId.postValue(updateUserRepository.getObjectId())
    }

    fun updateTimeZone() = viewModelScope.launch {
        updateUserRepository.updateUser(objectId.value.toString(), sessionToken.value.toString())
    }
}