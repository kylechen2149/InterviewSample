package com.kylechen2149.interviewsample.listInformation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylechen2149.interviewsample.InterviewSampleApp
import com.kylechen2149.interviewsample.R
import com.kylechen2149.interviewsample.SharedViewModel
import com.kylechen2149.interviewsample.adapter.ParkingInformationListDetailAdapter
import com.kylechen2149.interviewsample.databinding.FragmentListInformationBinding
import com.kylechen2149.interviewsample.listInformation.viewmodel.ListInformationViewModel
import com.kylechen2149.interviewsample.utils.HEADER_SESSION_TOKEN
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListInformationFragment : Fragment() {

    private val listInformationViewModel by viewModel<ListInformationViewModel>()

    private val sharedViewModel by activityViewModels<SharedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel.apply {
            toolbarTitle.value = getString(R.string.text_list_title)
            isShowToolbar.value = true
            isShowToolbarTitle.value = false
            isShowTimeZone.value = true
            isShowBack.value = false
        }
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
        viewModel = this@ListInformationFragment.listInformationViewModel.apply {
            getParkingInformation(this@ListInformationFragment.requireContext())
            parkingInformationList.observe(viewLifecycleOwner) {
               record.adapter = ParkingInformationListDetailAdapter(it.toMutableList(), this)
            }
        }
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
    }
}