package com.donymarulloh.testalodokter.ui.adapter.viewholder

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.donymarulloh.testalodokter.data.model.gambar.ImagesItem
import com.donymarulloh.testalodokter.databinding.ItemListBinding
import com.donymarulloh.testalodokter.ui.base.BaseViewItem
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

data class GambarItem(
    val title: String?,
    val desc: String?,
    val images: List<ImagesItem>?
    ) : BaseViewItem {
    fun compositeKey() = title + desc
}

class SourcesItemViewHolder(itemView : View) : RecyclerViewHolder<GambarItem>(itemView) {
    val binding: ItemListBinding = ItemListBinding.bind(itemView)
    override fun bind(position: Int, item: GambarItem) {
        super.bind(position, item)
        with(binding) {
            Log.d("url","${item!!.images.toString()}")
            val context = itemView.context
            Glide.with(context).load(item!!.images!![item!!.images!!.size-1]?.url_image).into(ivGambar)
        }
    }
}