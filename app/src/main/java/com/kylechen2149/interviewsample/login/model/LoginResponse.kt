package com.kylechen2149.interviewsample.login.model

data class LoginResponse(
    val objectId : String?,
    val name: String?,
    val timezone: String?,
    val phone: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val sessionToken: String?
)
