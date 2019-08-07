package com.whaletail.capecanaveral.itemDecorator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whaletail.capecanaveral.R
import kotlinx.android.synthetic.main.activity_item_decorator_view_holder_layout.view.*

class ItemDecoratorAdapter : RecyclerView.Adapter<ItemDecoratorAdapter.ViewHolder>() {

    val items = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 1, 1, 1, 1, 1, 1, 1, 1, 1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_item_decorator_view_holder_layout, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Int) {
            itemView.apply {
                tv_item.text = item.toString()
            }
        }
    }
}