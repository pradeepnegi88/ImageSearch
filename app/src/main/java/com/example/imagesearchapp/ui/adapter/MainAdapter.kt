package com.example.imagesearchapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearchapp.R
import com.example.imagesearchapp.domain.query.model.Photo
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val dataList: ArrayList<Photo>, private var listener: OnItemClickListener
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(item: Photo)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            data: Photo,
            listener: OnItemClickListener
        ) {
            itemView.setOnClickListener { listener.onItemClick(data) }
            Glide.with(itemView.imageViewAvatar.context)
                .load(data.imageUrl)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(dataList[position],listener)

    fun addData(list: List<Photo>) {
        dataList.clear()
        dataList.addAll(list)
    }
}