package com.donymarulloh.testalodokter.data.model.gambar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GambarResponse<T>(
    @Expose @SerializedName("data")
    val gambarList: List<Gambar>
)