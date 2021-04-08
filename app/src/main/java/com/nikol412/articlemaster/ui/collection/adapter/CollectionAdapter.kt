package com.nikol412.articlemaster.ui.collection.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.articlemaster.databinding.ItemCollectionBlockBinding
import com.nikol412.articlemaster.ui.overview.adapter.CollectionItem

class CollectionAdapter : RecyclerView.Adapter<CollectionViewHolder>() {
    private var contentBlocks = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            ItemCollectionBlockBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.onBind(contentBlocks[position])
    }

    override fun getItemCount(): Int = contentBlocks.size

    fun setItems(items: List<String>) {
        if (contentBlocks.isEmpty()) {
            contentBlocks = items.toMutableList()
            notifyDataSetChanged()
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean = contentBlocks[oldItemPosition] == items[newItemPosition]

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    contentBlocks[oldItemPosition] == items[newItemPosition]

                override fun getNewListSize(): Int = items.size

                override fun getOldListSize(): Int = contentBlocks.size
            })

            contentBlocks = items.toMutableList()
            result.dispatchUpdatesTo(this)
        }
    }
}

class CollectionViewHolder(private val binding: ItemCollectionBlockBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(block: String) {
        binding.textViewBlock.text = block
    }
}