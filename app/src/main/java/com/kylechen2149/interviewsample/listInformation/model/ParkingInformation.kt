package com.kylechen2149.interviewsample.listInformation.model

data class ParkingInformation(
    val id: String?,
    val name: String?,
    val address: String?,
    val totalCar: Int? = 0,
    val availableCar: Int? = 0,
    val charging: Int? = 0,
    val standby: Int? = 0
)
