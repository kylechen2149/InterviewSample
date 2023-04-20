package com.kylechen2149.interviewsample.listInformation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.kylechen2149.interviewsample.InterviewSampleApp
import com.kylechen2149.interviewsample.R
import com.kylechen2149.interviewsample.databinding.FragmentListInformationBinding
import com.kylechen2149.interviewsample.utils.HEADER_SESSION_TOKEN

class ListInformationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createDataBindingView(inflater, container)
    }

    private fun createDataBindingView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = DataBindingUtil.inflate<FragmentListInformationBinding>(
        inflater,
        R.layout.fragment_list_information,
        container,
        false
    ).apply {
        Log.d("Kyle", "token=${InterviewSampleApp.sharedPreferences.getString(HEADER_SESSION_TOKEN, "xxx")}")
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
    }
}