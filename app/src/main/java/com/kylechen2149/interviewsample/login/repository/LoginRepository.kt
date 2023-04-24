package com.kylechen2149.interviewsample.login.repository

import com.kylechen2149.interviewsample.InterviewSampleApp
import com.kylechen2149.interviewsample.login.datasource.LoginService
import com.kylechen2149.interviewsample.login.model.LoginRequest
import com.kylechen2149.interviewsample.login.model.LoginResponse
import com.kylechen2149.interviewsample.utils.HEADER_SESSION_TOKEN
import com.kylechen2149.interviewsample.utils.KEY_NAME
import com.kylechen2149.interviewsample.utils.KEY_OBJECT_ID
import com.kylechen2149.interviewsample.utils.KEY_TIME_ZONE
import retrofit2.Call

class LoginRepository(private val loginService: LoginService) {

    fun login(username: String, password: String): Call<LoginResponse> {
        return loginService.login(LoginRequest(username, password))
    }

    fun saveData(sessionToken: String?, name: String?, timezone: String?, objectId: String?): Boolean {
        return InterviewSampleApp.sharedPreferences.edit().apply {
            putString(HEADER_SESSION_TOKEN, sessionToken)
            putString(KEY_NAME, name)
            putString(KEY_TIME_ZONE, timezone)
            putString(KEY_OBJECT_ID, objectId)
        }.commit()
    }
}