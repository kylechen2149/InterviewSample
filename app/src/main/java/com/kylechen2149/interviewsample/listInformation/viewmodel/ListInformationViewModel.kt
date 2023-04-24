package com.kylechen2149.interviewsample.listInformation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylechen2149.interviewsample.listInformation.model.ParkingInformation
import com.kylechen2149.interviewsample.listInformation.repository.ListInformationRepository
import kotlinx.coroutines.launch

class ListInformationViewModel(private val listInformationRepository: ListInformationRepository): ViewModel() {

    val parkingInformationList = MutableLiveData<List<ParkingInformation>>()

    fun getParkingInformation(context: Context) = viewModelScope.launch {
        parkingInformationList.value = listInformationRepository.getParkingJson(context).toMutableList()
    }

}