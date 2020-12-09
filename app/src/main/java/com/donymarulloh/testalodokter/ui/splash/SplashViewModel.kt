package com.donymarulloh.testalodokter.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.donymarulloh.testalodokter.data.repository.Repository
import com.donymarulloh.testalodokter.ui.base.BaseViewModel
import com.donymarulloh.testalodokter.util.rx.SchedulerProvider
import java.util.concurrent.TimeUnit


class SplashViewModel(
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel(){


    private val _statusLogin = MutableLiveData<Boolean>()
    val statusLogin: LiveData<Boolean>
        get() = _statusLogin


    fun startSplash(){
        schedulerProvider.ui().scheduleDirect(Runnable {
            decideNextActivity()
        },1000, TimeUnit.MILLISECONDS)
    }


    private fun decideNextActivity(){
        if(appRepository.getEmail() == null){
            _statusLogin.postValue(false)
        }else {
            _statusLogin.postValue(true)
        }
    }
}