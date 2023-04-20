package com.kylechen2149.interviewsample.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.kylechen2149.interviewsample.R
import com.kylechen2149.interviewsample.databinding.FragmentLoginBinding
import com.kylechen2149.interviewsample.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()

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
    ) = DataBindingUtil.inflate<FragmentLoginBinding>(
        inflater,
        R.layout.fragment_login,
        container,
        false
    ).apply {
        viewModel = this@LoginFragment.loginViewModel.apply {
            email.observe(viewLifecycleOwner) { value ->
                if (value.isNullOrBlank())
                    tilEmail.error = getString(R.string.text_please_input_email)
                else if(!value.contains("@"))
                    tilEmail.error = getString(R.string.text_please_input_correct_email)
                else
                    tilEmail.isErrorEnabled = false
            }
            password.observe(viewLifecycleOwner) { pwd ->
                if(pwd.length !in 6..14)
                    tilPassword.error = getString(R.string.text_password_rule)
                else
                    tilPassword.isErrorEnabled = false
            }
            isLoading.observe(viewLifecycleOwner) {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
            loginBtnClick.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.action_loginFragment_to_listInformationFragment)
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