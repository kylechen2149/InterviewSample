package com.kylechen2149.interviewsample.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kylechen2149.interviewsample.adapter.ParkingInformationListDetailAdapter
import com.kylechen2149.interviewsample.listInformation.model.ParkingInformation
import com.kylechen2149.interviewsample.listInformation.viewmodel.ListInformationViewModel

@BindingAdapter("bind:visibility")
fun setVisibility(view: View, isVisibility: Boolean) {
    view.visibility = if (isVisibility) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bind:setVisibility")
fun setVisibilityGone(view: View, isVisibility: Boolean) {
    view.visibility = if (isVisibility) View.VISIBLE else View.GONE
}