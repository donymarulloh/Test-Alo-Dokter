package com.donymarulloh.testalodokter.di

import com.donymarulloh.testalodokter.data.repository.AppRepository
import com.donymarulloh.testalodokter.data.repository.Repository
import org.koin.dsl.module


val repositoryModule = module {
    factory<Repository> {
        AppRepository(get() , get())
    }
}