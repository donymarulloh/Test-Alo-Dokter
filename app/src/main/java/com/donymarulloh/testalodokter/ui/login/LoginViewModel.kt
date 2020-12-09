package com.donymarulloh.testalodokter.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.donymarulloh.testalodokter.data.model.login.DataLogin
import com.donymarulloh.testalodokter.data.model.login.LoginRequest
import com.donymarulloh.testalodokter.data.model.login.LoginResponse
import com.donymarulloh.testalodokter.data.repository.Repository
import com.donymarulloh.testalodokter.ui.adapter.viewholder.LoadingStateItem
import com.donymarulloh.testalodokter.ui.base.BaseViewItem
import com.donymarulloh.testalodokter.ui.base.BaseViewModel
import com.donymarulloh.testalodokter.util.Constant
import com.donymarulloh.testalodokter.util.RegexUtils.isValidEmail
import com.donymarulloh.testalodokter.util.SingleLiveEvent
import com.donymarulloh.testalodokter.util.ext.addTo
import com.donymarulloh.testalodokter.util.rx.SchedulerProvider

class LoginViewModel (
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel(){

    private val _loginLiveData = MutableLiveData<LoginResponse>()
    val loginLiveData: LiveData<LoginResponse> get() = _loginLiveData



    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val errorMessage = SingleLiveEvent<String>()


    fun doLogin(userName: String, passWord: String) {
        val isUsernameValid = isValidEmail(userName)
        val isPassWordValid = passWord.trim().length > 4
        if (isUsernameValid && !isPassWordValid) {
            errorMessage.postValue(Constant.ERROR_PASSWORD_MESSAGE)
        } else if (!isUsernameValid && isPassWordValid) {
            errorMessage.postValue(Constant.ERROR_EMAIL_MESSAGE)
        } else if (!isUsernameValid && !isPassWordValid) {
            errorMessage.postValue(Constant.ERROR_EMAIL_PASSWORD_MESSAGE)
        } else {


            appRepository.login(LoginRequest(userName,passWord))
                .doOnSubscribe { _loading.postValue(true) }
                .doFinally {
                    _loading.postValue(false)
                }
                .subscribe({
                    _loginLiveData.postValue(it.data)
                }, {
                    it.printStackTrace()
                    errorMessage.postValue(Constant.ERROR_MESSAGE)
                })
                .addTo(compositeDisposable)
        }
    }


}

