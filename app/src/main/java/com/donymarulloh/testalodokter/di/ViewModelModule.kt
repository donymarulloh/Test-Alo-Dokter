package com.donymarulloh.testalodokter.di

import com.donymarulloh.testalodokter.ui.detail.DetailViewModel
import com.donymarulloh.testalodokter.ui.login.LoginViewModel
import com.donymarulloh.testalodokter.ui.main.MainViewModel
import com.donymarulloh.testalodokter.ui.main.home.HomeViewModel
import com.donymarulloh.testalodokter.ui.main.profile.ProfileViewModel
import com.donymarulloh.testalodokter.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get() , get()) }
    viewModel { LoginViewModel(get() , get()) }
    viewModel { MainViewModel(get() , get()) }
    viewModel { HomeViewModel(get() , get()) }
    viewModel { DetailViewModel(get() , get()) }
    viewModel { ProfileViewModel(get() , get()) }
}