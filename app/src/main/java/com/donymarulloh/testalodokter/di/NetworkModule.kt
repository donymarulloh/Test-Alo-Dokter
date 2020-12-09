package com.donymarulloh.testalodokter.di

import com.donymarulloh.testalodokter.BuildConfig
import com.donymarulloh.testalodokter.data.source.remote.Api
import com.donymarulloh.testalodokter.data.source.remote.AppRemoteSource
import com.donymarulloh.testalodokter.util.Constant
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient().newBuilder()
            .connectTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    single {
        AppRemoteSource(get())
    }

    single{
        Retrofit.Builder().baseUrl("https://api.mocki.io/v1/")
            .client(get<OkHttpClient>())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(Api::class.java)
    }

}