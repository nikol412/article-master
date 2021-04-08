package com.nikol412.articlemaster.ui.collection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikol412.articlemaster.ui.overview.adapter.CollectionItem

class CollectionViewModel: ViewModel() {
    val collection = MutableLiveData<CollectionItem>()

    init {
//        collection.value = CollectionItem("Title", listOf("Link 1", "Link 2", "Link 3"))
    }
}