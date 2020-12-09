package com.donymarulloh.testalodokter.data.source.remote

import com.donymarulloh.testalodokter.data.model.login.LoginRequest


class AppRemoteSource(private val api: Api){

    fun loginUser(loginRequest: LoginRequest) = api.loginUser(loginRequest)
    fun gambar() = api.gambar()

}