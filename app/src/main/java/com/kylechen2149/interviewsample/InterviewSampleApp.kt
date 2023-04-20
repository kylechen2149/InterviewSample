package com.kylechen2149.interviewsample

import android.app.Application
import android.content.SharedPreferences
import com.kylechen2149.interviewsample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class InterviewSampleApp : Application(){

    companion object {
        private lateinit var privateInstance: InterviewSampleApp
        val instance: InterviewSampleApp
            get() = privateInstance

        lateinit var sharedPreferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        privateInstance = this

        sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)

        startKoin {
            //androidLogger()
            androidContext(this@InterviewSampleApp)
            modules(koinModules)
        }
    }
}