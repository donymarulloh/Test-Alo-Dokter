package com.donymarulloh.testalodokter

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.donymarulloh.testalodokter.di.*
import com.jakewharton.threetenabp.AndroidThreeTen
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class AppController : MultiDexApplication() {

    private val calConfig: CalligraphyConfig by inject()

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this);

        startKoin {
            androidContext(this@AppController)
            modules(networkModule)
            modules(preferenceModule)
            modules(repositoryModule)
            modules(appModule)
            modules(viewModelModule)
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        CalligraphyConfig.initDefault(calConfig)
        Hawk.init(applicationContext).setLogInterceptor { message ->
            if (BuildConfig.DEBUG) {
                Log.d("Hawk", message)
            }
        }.build()
    }
}