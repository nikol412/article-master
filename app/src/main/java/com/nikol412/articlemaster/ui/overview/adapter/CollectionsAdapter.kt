package com.nikol412.articlemaster.ui.overview.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.articlemaster.databinding.ItemCollectionRowBinding

class CollectionsAdapter(private val onCollectionClick: OnCollectionClick) :
    RecyclerView.Adapter<CollectionViewHolder>() {
    private var collectionsList = mutableListOf<CollectionItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            ItemCollectionRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

class CollectionViewHolder(private val binding: ItemCollectionRowBinding) :
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeStringList(contentBlocks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CollectionItem> {
        override fun createFromParcel(parcel: Parcel): CollectionItem {
            return CollectionItem(parcel)
        }

        override fun newArray(size: Int): Array<CollectionItem?> {
            return arrayOfNulls(size)
        }
    }
}