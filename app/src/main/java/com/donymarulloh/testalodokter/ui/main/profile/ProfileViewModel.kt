package com.donymarulloh.testalodokter.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.donymarulloh.testalodokter.data.repository.Repository
import com.donymarulloh.testalodokter.ui.base.BaseViewModel
import com.donymarulloh.testalodokter.util.ext.addTo
import com.donymarulloh.testalodokter.util.rx.SchedulerProvider

class ProfileViewModel  (
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {


    private val _statusLogout = MutableLiveData<Boolean>()
    val statusLogout: LiveData<Boolean>
        get() = _statusLogout


    fun doLogout(){
        appRepository.logout()
            .subscribeOn(schedulerProvider.ui())
            .subscribe({
                _statusLogout.postValue(true)
            }, {
                _statusLogout.postValue(false)
            })
            .addTo(compositeDisposable)
    }


}
