package com.nikol412.articlemaster.ui.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.articlemaster.databinding.ItemCollectionBinding

class CollectionsAdapter(private val onCollectionClick: OnCollectionClick) :
    RecyclerView.Adapter<CollectionViewHolder>() {
    private var collectionsList = mutableListOf<CollectionItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.onBind(collectionsList[position], onCollectionClick)
    }

    override fun getItemCount(): Int = collectionsList.size

    fun setItems(items: List<CollectionItem>) {
        if (collectionsList.isEmpty()) {
            collectionsList = items.toMutableList()
            notifyDataSetChanged()
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean = collectionsList[oldItemPosition] == items[newItemPosition]

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    collectionsList[oldItemPosition] == items[newItemPosition]

                override fun getNewListSize(): Int = items.size

                override fun getOldListSize(): Int = collectionsList.size
            })

            collectionsList = items.toMutableList()
            result.dispatchUpdatesTo(this)
        }
    }
}

class CollectionViewHolder(private val binding: ItemCollectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: CollectionItem, listener: OnCollectionClick) {
        with(binding) {
            rootLayout.setOnClickListener {
                listener.onClick(item)
            }
            textViewTitle.text = item.title
            textViewContent.text = item.contentBlocks.first()
        }
    }
}

interface OnCollectionClick {
    fun onClick(item: CollectionItem)
}

data class CollectionItem(
    val title: String,
    val contentBlocks: List<String>
)