package com.donymarulloh.testalodokter.data.model.gambar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Gambar(
    @Expose @SerializedName("desc") val desc: String? = null,
    @Expose @SerializedName("title") val title: String? = null,
    @Expose @SerializedName("images") val images: List<ImagesItem>? = null
)

