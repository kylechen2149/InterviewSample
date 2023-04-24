package com.kylechen2149.interviewsample.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kylechen2149.interviewsample.R
import com.kylechen2149.interviewsample.SharedViewModel
import com.kylechen2149.interviewsample.databinding.FragmentLoginBinding
import com.kylechen2149.interviewsample.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel.apply {
            isShowToolbar.value = false
        }
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
        binding = this
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
        }
    }.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.apply {
            loginBtnClick.observe(this@LoginFragment) { loginSuccess ->
                if(loginSuccess) {
                    findNavController().navigate(R.id.action_loginFragment_to_listInformationFragment)
                }
            }
        }
    }
}