package com.nikol412.articlemaster.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikol412.articlemaster.R
import com.nikol412.articlemaster.ui.collection.adapter.CollectionAdapter
import com.nikol412.articlemaster.ui.overview.COLLECTION
import com.nikol412.articlemaster.ui.overview.adapter.CollectionItem

class CollectionFragment: Fragment() {
    private val viewModel by viewModels<CollectionViewModel>()

    private val adapter by lazy {
        CollectionAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collection, container, false)
        val recyclerVIew = view.findViewById<RecyclerView>(R.id.recycler_view_blocks)

        recyclerVIew.layoutManager = LinearLayoutManager(requireContext())
        recyclerVIew.adapter = adapter

        arguments?.getParcelable<CollectionItem>(COLLECTION)?.let { collection ->
            viewModel.collection.value = collection
        }
        viewModel.collection.observe(viewLifecycleOwner) { collection ->
            adapter.setItems(collection.contentBlocks)
            view.findViewById<TextView>(R.id.text_view_title).text = collection.title
        }

        return view
    }
}