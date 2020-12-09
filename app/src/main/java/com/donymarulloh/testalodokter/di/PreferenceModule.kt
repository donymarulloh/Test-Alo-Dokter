package com.donymarulloh.testalodokter.di

import com.donymarulloh.testalodokter.data.source.pref.AppPrefSource
import org.koin.dsl.module

val preferenceModule = module {
    single {
        AppPrefSource()
    }

}
