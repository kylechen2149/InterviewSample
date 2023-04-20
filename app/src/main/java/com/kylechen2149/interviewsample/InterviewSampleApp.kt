package com.kylechen2149.interviewsample

import android.app.Application
import android.content.SharedPreferences
import com.kylechen2149.interviewsample.di.*
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

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
        initLogger()
        startKoin {
            //androidLogger()
            androidContext(this@InterviewSampleApp)
            modules(koinModules)
        }
    }

    private fun initLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, tag, message, t)
            }
        })
    }
}