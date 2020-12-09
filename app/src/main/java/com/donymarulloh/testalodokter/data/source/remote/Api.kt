package com.donymarulloh.testalodokter.data.source.remote

import com.donymarulloh.testalodokter.data.model.gambar.Gambar
import com.donymarulloh.testalodokter.data.model.gambar.GambarResponse
import com.donymarulloh.testalodokter.data.model.login.LoginRequest
import com.donymarulloh.testalodokter.data.model.login.LoginResponse
import io.reactivex.Observable
import retrofit2.http.*

@JvmSuppressWildcards
interface Api {

    @Headers("Content-Type: application/json")
    @POST("c67f56ac")
    fun loginUser(@Body loginRequest: LoginRequest): Observable<LoginResponse>


    @GET("59e5da40")
    fun gambar(): Observable<GambarResponse<List<Gambar>>>

}