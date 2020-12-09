package com.donymarulloh.testalodokter.data.model.login

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("email") val email : String?,
    @SerializedName("password") val password : String?
)