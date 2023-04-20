package com.kylechen2149.interviewsample.di

import com.kylechen2149.interviewsample.login.datasource.LoginService
import com.kylechen2149.interviewsample.login.repository.LoginRepository
import com.kylechen2149.interviewsample.login.viewmodel.LoginViewModel
import com.kylechen2149.interviewsample.network.getRetrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single { getRetrofit(get()) }
}

val apiModule = module {
    single<LoginService> { get<Retrofit>().create(LoginService::class.java) }
//    single<Liveroom> { get<Retrofit>().create(Liveroom::class.java) }
//    single<Common> { get<Retrofit>().create(Common::class.java) }
}

val repoModule = module {
    single { LoginRepository(get()) }
//    single { LiveroomRepo(get()) }
//    single { CommonRepo(get()) }
}

val viewModelModule = module {
//    viewModel { SharedViewModel() }
    viewModel { LoginViewModel(get()) }
//    viewModel { MainViewModel() }
//    viewModel { CreateRoomViewModel(get(), get()) }
//    viewModel { MyLiveViewModel(get()) }
}

val koinModules = listOf(appModule, apiModule, repoModule, viewModelModule)