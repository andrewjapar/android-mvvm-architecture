package com.andrewjapar.android_mvvm_architecture.di

import com.andrewjapar.android_mvvm_architecture.ArchApplication
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Module
class NetworkModule(
    private val serverUrl: String,
    private val baseTimeout: Long
) {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ArchApplication.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.from(Executors.newCachedThreadPool()))
            )
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return okhttp3.OkHttpClient()
            .newBuilder()
            .connectTimeout(baseTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(baseTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(baseTimeout, TimeUnit.MILLISECONDS)
            .build()
    }
}