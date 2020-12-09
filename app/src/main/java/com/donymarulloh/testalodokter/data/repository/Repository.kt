package com.donymarulloh.testalodokter.data.repository

import com.donymarulloh.testalodokter.data.model.gambar.Gambar
import com.donymarulloh.testalodokter.data.model.login.LoginRequest
import com.donymarulloh.testalodokter.data.model.login.LoginResponse
import io.reactivex.Completable
import io.reactivex.Observable

interface Repository {
    fun logout(): Completable
    fun getEmail(): String?
    fun login(loginRequest: LoginRequest): Observable<Result<LoginResponse>>
    fun getGambar(): Observable<List<Gambar>>
}