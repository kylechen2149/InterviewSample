package com.kylechen2149.interviewsample.updateuser.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylechen2149.interviewsample.R
import com.kylechen2149.interviewsample.updateuser.model.UpdateUserResponse
import com.kylechen2149.interviewsample.updateuser.repository.UpdateUserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class UpdateUserViewModel(private val updateUserRepository: UpdateUserRepository) : ViewModel() {

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

    fun updateTimeZone(context: Context) = viewModelScope.launch {
        updateUserRepository.updateUser(objectId.value.toString(), sessionToken.value.toString())
            .enqueue(object : Callback<UpdateUserResponse> {
                override fun onResponse(
                    call: Call<UpdateUserResponse>,
                    response: Response<UpdateUserResponse>
                ) {
                    Toast.makeText(context,
                        if (response.isSuccessful) context.getString(R.string.text_update_timezone_msg) else response.errorBody()
                            ?.string(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                    Timber.e(t.message)
                }
            })

    }
}