package com.kylechen2149.interviewsample

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.kylechen2149.interviewsample.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val sharedViewModel by viewModel<SharedViewModel>()
    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            binding = this

            viewModel = sharedViewModel.apply {
                toolbarBack.observe(this@MainActivity) {
                    if (it)
                        onBackClicked()
                }
                timeZoneClick.observe(this@MainActivity) {
//                    if(it){
//                        findNavController(R.id.nav_host_fragment_content_main).popBackStack(
//                            R.id.timeZoneFragment,
//                            true
//                        )
//                        findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.timeZoneFragment)
//                    }
                }
            }

            lifecycleOwner = this@MainActivity
        }
        setSupportActionBar(binding.toolBar)
    }

    private fun onBackClicked() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
        val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount

        if (backStackEntryCount == 0
            || navController.currentDestination?.id == R.id.listInformationFragment
        ) {
            finish()
            return
        }

        navController.popBackStack()
    }
}