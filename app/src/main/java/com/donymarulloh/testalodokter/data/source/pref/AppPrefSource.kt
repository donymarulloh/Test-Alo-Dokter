package com.donymarulloh.testalodokter.data.source.pref

import com.donymarulloh.testalodokter.data.model.LoginOverview
import com.donymarulloh.testalodokter.util.CacheKey
import com.orhanobut.hawk.Hawk

class AppPrefSource {
    fun setEmail(email: String) = Hawk.put(CacheKey.LOGIN, email)
    fun getEmail() : String? = Hawk.get(CacheKey.LOGIN,null)
    fun setClearLoginOverview() = Hawk.put(CacheKey.LOGIN, null)
}