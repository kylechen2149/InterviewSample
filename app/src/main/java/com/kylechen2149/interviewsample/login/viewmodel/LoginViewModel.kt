package com.kylechen2149.interviewsample.login.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.kylechen2149.interviewsample.InterviewSampleApp
import com.kylechen2149.interviewsample.login.model.LoginResponse
import com.kylechen2149.interviewsample.login.repository.LoginRepository
import com.kylechen2149.interviewsample.utils.HEADER_SESSION_TOKEN
import com.kylechen2149.interviewsample.utils.KEY_NAME
import com.kylechen2149.interviewsample.utils.KEY_TIME_ZONE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LoginViewModel(private val loginRepository: LoginRepository): ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginBtnClick = MutableLiveData<Unit>()
    val loginBtnClick: LiveData<Unit> = _loginBtnClick

    fun onSignInClick(context: Context) = viewModelScope.launch {
        _isLoading.value = true
        val response: Call<LoginResponse> = loginRepository.login(email.value.toString(), password.value.toString())
        response.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    Timber.d("res = $res")
                    res?.let {
                        loginRepository.saveData(it.sessionToken, it.name, it.timezone, it.objectId)
                    }
                    _loginBtnClick.postValue(Unit)
                } else {
                    try {
                        response.errorBody()?.let {
                            Toast.makeText(context, it.string(), Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {

                    }
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }
        })
        _isLoading.value = false
    }
}