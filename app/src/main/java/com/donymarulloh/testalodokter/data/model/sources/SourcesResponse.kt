package com.donymarulloh.newsapp.data.model.sources

import com.donymarulloh.newsapp.data.model.sources.Sources
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourcesResponse<T>(
    @Expose @SerializedName("sources")
    val sourcesItem: List<Sources>
)