package com.kylechen2149.interviewsample.updateuser.datasource

import com.kylechen2149.interviewsample.InterviewSampleApp
import com.kylechen2149.interviewsample.updateuser.model.UpdateUserResponse
import com.kylechen2149.interviewsample.utils.HEADER_SESSION_TOKEN
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Url

interface UpdateUserService {

    @PUT
    suspend fun updateUser(
        @Url url: String,
        @Header(HEADER_SESSION_TOKEN) token: String
    ): UpdateUserResponse
}