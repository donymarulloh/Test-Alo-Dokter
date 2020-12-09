package com.donymarulloh.testalodokter.data.repository

import com.donymarulloh.testalodokter.data.source.remote.AppRemoteSource
import com.donymarulloh.testalodokter.data.model.gambar.Gambar
import com.donymarulloh.testalodokter.data.model.login.LoginRequest
import com.donymarulloh.testalodokter.data.model.login.LoginResponse
import com.donymarulloh.testalodokter.data.source.pref.AppPrefSource
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

data class Result<out T>(
    val data: T? = null,
    val error: Throwable? = null
)

fun <T> Observable<T>.responseToResult(): Observable<Result<T>> {
    return map { it.asResult() }
        .onErrorReturn {
            when (it) {
                is HttpException,
                is SocketTimeoutException,
                is UnknownHostException -> {
                    it.asErrorResult()
                }
                else -> throw it
            }
        }
}

fun <T> T.asResult(): Result<T> = Result(data = this, error = null)
fun <T> Throwable.asErrorResult(): Result<T> = Result(data = null, error = this)

open class AppRepository constructor(
    private val api: AppRemoteSource,
    private val pref: AppPrefSource
) : Repository {

    override fun logout(): Completable {
        return Completable.create {
            if (pref.setClearLoginOverview()) it.onComplete()
            else it.onError(Throwable("Not able to remove"))
        }
    }

    override fun getEmail(): String? = pref.getEmail()

    override fun login(loginRequest: LoginRequest): Observable<Result<LoginResponse>> {
        val remoteObservable = api.loginUser(loginRequest)
            .flatMap {
                loginRequest?.email?.let { it1 -> setEmail(it1) }
                Observable.just(it.asResult())
            }
            .onErrorResumeNext { t: Throwable ->
                 Observable.error(t)
            }

        return Observable.concatArrayEager(remoteObservable)
    }

    override fun getGambar(): Observable<List<Gambar>> {
        val remoteObservable = api.gambar()
            .flatMap {
                Observable.just(it.gambarList)
            }
        return Observable.concatArrayEager(remoteObservable)
    }




    private fun setEmail(email: String) = pref.setEmail(email)



}