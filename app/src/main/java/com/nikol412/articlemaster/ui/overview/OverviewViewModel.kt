package com.nikol412.articlemaster.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikol412.articlemaster.ui.overview.adapter.CollectionItem

class OverviewViewModel: ViewModel() {

    val collections = MutableLiveData(mutableListOf<CollectionItem>())

    init {
        val testList = (1..10).map { CollectionItem("$it") }
        collections.value = testList.toMutableList()
    }
}