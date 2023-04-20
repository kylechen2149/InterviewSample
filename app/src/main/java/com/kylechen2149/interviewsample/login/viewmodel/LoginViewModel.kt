package com.kylechen2149.interviewsample.login.viewmodel

import androidx.lifecycle.*
import com.kylechen2149.interviewsample.InterviewSampleApp
import com.kylechen2149.interviewsample.login.repository.LoginRepository
import com.kylechen2149.interviewsample.utils.HEADER_SESSION_TOKEN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(private val loginRepository: LoginRepository): ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _loginBtnClick = MutableLiveData<Unit>()
    val loginBtnClick: LiveData<Unit>
        get() = _loginBtnClick

    fun onSignInClick() = viewModelScope.launch {
        val response = loginRepository.login(email.value.toString(), password.value.toString())
        response.let {
            InterviewSampleApp.sharedPreferences.edit().putString(HEADER_SESSION_TOKEN, it.sessionToken).apply()
        }
        _loginBtnClick.postValue(Unit)
    }
}