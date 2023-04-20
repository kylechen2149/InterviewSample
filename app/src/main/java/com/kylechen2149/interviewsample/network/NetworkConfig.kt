package com.kylechen2149.interviewsample.network

import com.kylechen2149.interviewsample.utils.BASE_URL
import com.kylechen2149.interviewsample.utils.HEADER_ID_KEY
import com.kylechen2149.interviewsample.utils.HEADER_ID_VALUE
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun getTokenInterceptor() = Interceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader(HEADER_ID_KEY, HEADER_ID_VALUE)
        .build()

    return@Interceptor chain.proceed(request)
}

private fun getOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(getHttpLoggingInterceptor())
    .addInterceptor(getTokenInterceptor())
    .build()

private fun getMoshiConverterFactory(moshi: Moshi? = null): MoshiConverterFactory {
    return moshi?.let { MoshiConverterFactory.create(it) } ?: MoshiConverterFactory.create()
}

fun getRetrofit(moshi: Moshi? = null): Retrofit = Retrofit.Builder()
    .client(getOkHttpClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(getMoshiConverterFactory(moshi))
    .build()