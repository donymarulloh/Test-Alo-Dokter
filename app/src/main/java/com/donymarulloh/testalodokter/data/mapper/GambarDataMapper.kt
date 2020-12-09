package com.donymarulloh.testalodokter.data.mapper

import com.donymarulloh.testalodokter.data.model.gambar.Gambar
import com.donymarulloh.testalodokter.ui.adapter.viewholder.GambarItem


object GambarDataMapper {

    fun transform(responses: List<Gambar>?) = responses?.map { response ->
        GambarItem(
            response.title,
            response.desc,
            response.images)
    }.orEmpty()
}
