package com.donymarulloh.testalodokter.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginOverview(
    @Expose @SerializedName("token") val token: Int? = null,
    @Expose @SerializedName("nama") val nama: String? = null

    )