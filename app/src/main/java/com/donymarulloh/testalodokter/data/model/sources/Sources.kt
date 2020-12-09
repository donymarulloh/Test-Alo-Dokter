package com.donymarulloh.newsapp.data.model.sources

 import android.os.Parcelable
 import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
 import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sources(
    @Expose @SerializedName("id") val idSources: String? = null,
    @Expose @SerializedName("name") val nameSources: String? = null,
    @Expose @SerializedName("description") val descriptionSources: String? = null,
    @Expose @SerializedName("category") val categorySources: String? = null,
    @Expose @SerializedName("url") val urlSources: String? = null,
    @Expose @SerializedName("language") val languageSources: String? = null,
    @Expose @SerializedName("country") val countrySources: String? = null
) : Parcelable {
    val name get() = nameSources + if (!idSources.isNullOrEmpty()) ", $idSources" else ""
    val compositeKey get() = nameSources + idSources

}