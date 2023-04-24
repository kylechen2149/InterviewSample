package com.kylechen2149.interviewsample.updateuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kylechen2149.interviewsample.InterviewSampleApp
import com.kylechen2149.interviewsample.R
import com.kylechen2149.interviewsample.SharedViewModel
import com.kylechen2149.interviewsample.databinding.FragmentUpdateUserBinding
import com.kylechen2149.interviewsample.updateuser.viewmodel.UpdateUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class UpdateUserFragment : Fragment() {

    private val updateUserViewModel by viewModel<UpdateUserViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel.apply {
            isShowToolbar.value = true
            isShowToolbarTitle.value = false
            isShowTimeZone.value = false
            isShowBack.value = true
        }
        return createDataBindingView(inflater, container)
    }

    private fun createDataBindingView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = DataBindingUtil.inflate<FragmentUpdateUserBinding>(
        inflater,
        R.layout.fragment_update_user,
        container,
        false
    ).apply {
        viewModel = this@UpdateUserFragment.updateUserViewModel.apply {
            getUserData()
            email.observe(viewLifecycleOwner) {
                tvEmail.text = getString(R.string.text_email_with_parameter, it)
            }
            timeZone.observe(viewLifecycleOwner) {
                if(it.isNotEmpty()){
                    val tz = mutableListOf<String>()
                    tz.add("Time zone")
                    tz.add(it)
                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, tz)
                    spinner.adapter = adapter
                    spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                            updateTimeZone()
                        }
                        override fun onNothingSelected(parent: AdapterView<*>) {}
                    }
                }
            }
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}