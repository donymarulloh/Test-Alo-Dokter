package com.donymarulloh.testalodokter.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: String?
)