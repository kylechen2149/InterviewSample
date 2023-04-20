package com.kylechen2149.interviewsample.listInformation.model

data class ParkingAvailable(
    val id: String?,
    val availablecar: Int? = 0,
    val charging: Int? = 0,
    val standby: Int? = 0
)
