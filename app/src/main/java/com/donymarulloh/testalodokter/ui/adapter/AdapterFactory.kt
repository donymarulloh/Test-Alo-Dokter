package com.donymarulloh.testalodokter.ui.adapter

import android.view.View
import com.donymarulloh.testalodokter.R
import com.donymarulloh.testalodokter.ui.adapter.viewholder.ErrorStateItemViewHolder
import com.donymarulloh.testalodokter.ui.adapter.viewholder.LoadingStateItemViewHolder
import com.donymarulloh.testalodokter.ui.adapter.viewholder.SourcesItemViewHolder
import com.donymarulloh.testalodokter.ui.base.BaseViewItem
import me.ibrahimyilmaz.kiel.adapterOf

typealias OnItemClickListener = (BaseViewItem, View) -> Unit
typealias OnItemLongClickListener = (BaseViewItem, View) -> Unit

fun createAdapter(
    onItemClick: OnItemClickListener? = null,
    onItemLongClickListener: OnItemLongClickListener? = null
) = adapterOf<BaseViewItem> {
    register(
        layoutResource = R.layout.item_error_state,
        viewHolder = ::ErrorStateItemViewHolder,
        onBindViewHolder = { errorStateItemViewHolder, _, errorStateItem ->
            errorStateItemViewHolder.binding.textRetry.setOnClickListener {
                onItemClick?.invoke(
                    errorStateItem,
                    it
                )
            }
        }
    )
    register(
        layoutResource = R.layout.item_loading_state,
        viewHolder = ::LoadingStateItemViewHolder
    )

    register(
        layoutResource = R.layout.item_sources,
        viewHolder = ::SourcesItemViewHolder,
        onBindViewHolder = { sourcesItemHolder, _, sourcesItem ->
            with(sourcesItemHolder) {
                binding.root.setOnClickListener { onItemClick?.invoke(sourcesItem, it) }
            }
        }
    )


}
