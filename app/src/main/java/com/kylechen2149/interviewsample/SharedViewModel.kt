package com.kylechen2149.interviewsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    var isShowToolbar : MutableLiveData<Boolean> = MutableLiveData()
}