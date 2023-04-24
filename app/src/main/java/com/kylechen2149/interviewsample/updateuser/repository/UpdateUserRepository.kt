package com.kylechen2149.interviewsample.updateuser.repository

import com.kylechen2149.interviewsample.InterviewSampleApp
import com.kylechen2149.interviewsample.updateuser.datasource.UpdateUserService
import com.kylechen2149.interviewsample.updateuser.model.UpdateUserResponse
import com.kylechen2149.interviewsample.utils.HEADER_SESSION_TOKEN
import com.kylechen2149.interviewsample.utils.KEY_NAME
import com.kylechen2149.interviewsample.utils.KEY_OBJECT_ID
import com.kylechen2149.interviewsample.utils.KEY_TIME_ZONE

class UpdateUserRepository(private val updateUserService: UpdateUserService) {

    suspend fun updateUser(id: String, sessionToken: String) : UpdateUserResponse{
        return updateUserService.updateUser("/api/users/$id", sessionToken)
    }

    fun getObjectId() : String? {
        return InterviewSampleApp.sharedPreferences.getString(KEY_OBJECT_ID, "")
    }

    fun getToken() : String? {
        return InterviewSampleApp.sharedPreferences.getString(HEADER_SESSION_TOKEN, "")
    }

    fun getUserName() : String? {
       return InterviewSampleApp.sharedPreferences.getString(KEY_NAME, "")
    }

    fun getTimeZone() : String? {
        return InterviewSampleApp.sharedPreferences.getString(KEY_TIME_ZONE, "")
    }
}