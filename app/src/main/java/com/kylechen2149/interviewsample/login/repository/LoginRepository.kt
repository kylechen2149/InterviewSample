package com.kylechen2149.interviewsample.login.repository

import com.kylechen2149.interviewsample.login.datasource.LoginService
import com.kylechen2149.interviewsample.login.model.LoginRequest
import com.kylechen2149.interviewsample.login.model.LoginResponse

class LoginRepository(private val loginService: LoginService) {

    suspend fun login(username: String, password: String): LoginResponse {
        return loginService.login(LoginRequest(username, password))
    }
}