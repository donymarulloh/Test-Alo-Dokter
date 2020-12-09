package com.donymarulloh.testalodokter.di

import androidx.recyclerview.widget.LinearLayoutManager
import com.donymarulloh.testalodokter.R
import com.donymarulloh.testalodokter.util.rx.AppSchedulerProvider
import com.donymarulloh.testalodokter.util.rx.SchedulerProvider
import org.koin.dsl.module
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

const val DEFAULT_FONT = "fonts/GoogleSans-Regular.ttf"

val appModule = module {
    single {
        CalligraphyConfig.Builder().setDefaultFontPath(DEFAULT_FONT).setFontAttrId(R.attr.fontPath)
            .build()
    }

    factory<SchedulerProvider> {
        AppSchedulerProvider()
    }

    factory {
        LinearLayoutManager(get())
    }
}