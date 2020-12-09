package com.donymarulloh.testalodokter.data.model.gambar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImagesItem(
    @Expose @SerializedName("url") val url_image: String? = null)