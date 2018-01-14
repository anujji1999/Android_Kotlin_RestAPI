package com.codingblocks.restapiconsumer.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.layoutInflater

/**
 * Created by championswimmer on 14/01/18.
 */
class GenericAnkoAdapter<T, A: AnkoComponent<ViewGroup>> (
        val items: Array<T>,
        val ankoType: () -> A,
        val bindView: (T, A) -> Unit
): RecyclerView.Adapter<GenericAnkoAdapter<T,A>.GenericViewHolder> () {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder?, position: Int) {
        holder?.view?.let { bindView(items[position], holder.ankoView) }
    }

    override fun onCreateViewHolder(
            parent: ViewGroup?,
            viewType: Int
    ): GenericViewHolder = with (ankoType()) {
        GenericViewHolder(
                createView(AnkoContext.create(parent!!.context, parent)),
                this
        )
    }

    inner class GenericViewHolder(
            val view: View?,
            val ankoView: A
    ): RecyclerView.ViewHolder(view)
}