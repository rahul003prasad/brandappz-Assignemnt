package com.example.michaeljacksonsong

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.michaeljacksonsong.databinding.ItemCollectionBinding

class CollectionAdapter(private val iOnItemClickListener: IOnItemClickListener) : ListAdapter<Collection, CollectionAdapter.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCollectionBinding
                .inflate(LayoutInflater.from(parent.context), parent, false).root
        )

    }


    override fun onBindViewHolder(holder: CollectionAdapter.MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            tvTrackName.text = item.trackName
            tvGenre.text = item.primaryGenreName

            Glide.with(holder.itemView.context).load(item.image).into(imageview)

        }

        holder.itemView.setOnClickListener {
            iOnItemClickListener.onItemClick(item)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Collection>() {
        override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem == newItem
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCollectionBinding.bind(itemView)
    }


    interface IOnItemClickListener {
        fun onItemClick(item: Collection)
    }
}