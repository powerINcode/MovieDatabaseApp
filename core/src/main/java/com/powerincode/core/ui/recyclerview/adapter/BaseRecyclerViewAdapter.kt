package com.powerincode.core.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>() : RecyclerView.Adapter<BaseRecyclerViewAdapter<T>.BaseViewHolder>() {
    private var items: List<T> = emptyList()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return onCreateViewHolder(LayoutInflater.from(parent.context), parent)
    }

    abstract fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isNotEmpty()) {
            holder.onBind(payloads)
        } else {
            holder.onBind(items[position])
        }
    }

    fun swapData(data: List<T>) {
        val oldList = items.toList()
        items = data
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == data[newItemPosition]

            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = data.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == data[newItemPosition]

        }).dispatchUpdatesTo(this)
    }

    abstract inner class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {
        abstract fun onBind(data: T)
        open fun onBind(payloads: List<Any>) {}

    }
}