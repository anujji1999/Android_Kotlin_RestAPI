package com.codingblocks.restapiconsumer.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.layoutInflater

/**
 * Created by championswimmer on 14/01/18.
 */
class GenericAdapter<T> (
        val items: Array<T>,
        val layoutResId: Int,
        val bindView: (T, View) -> Unit
): RecyclerView.Adapter<GenericAdapter.GenericViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder?, position: Int) {
        holder?.itemView?.let { bindView(items[position], it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GenericViewHolder {
        return parent?.context?.layoutInflater?.inflate(
                layoutResId,
                parent,
                false
        ).let {
            GenericViewHolder(it)
        }
    }

    class GenericViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView)
}