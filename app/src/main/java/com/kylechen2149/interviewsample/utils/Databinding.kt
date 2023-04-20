package com.kylechen2149.interviewsample.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:visibility")
fun setVisibility(view: View, isVisibility: Boolean) {
    view.visibility = if (isVisibility) View.VISIBLE else View.INVISIBLE
}