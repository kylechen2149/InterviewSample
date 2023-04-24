package com.kylechen2149.interviewsample.login.datasource

import com.kylechen2149.interviewsample.login.model.LoginRequest
import com.kylechen2149.interviewsample.login.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/api/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

}