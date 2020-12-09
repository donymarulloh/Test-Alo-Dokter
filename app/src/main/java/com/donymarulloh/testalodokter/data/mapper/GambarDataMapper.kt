package com.donymarulloh.testalodokter.data.mapper

import com.donymarulloh.newsapp.data.model.sources.Sources
import com.donymarulloh.testalodokter.data.model.gambar.Gambar
import com.donymarulloh.testalodokter.ui.adapter.viewholder.GambarItem


object SourcesDataMapper {

    fun transform(responses: List<Gambar>?) = responses?.map { response ->
        GambarItem(
            response.title,
            response.desc,
            response.images)
    }.orEmpty()
}
