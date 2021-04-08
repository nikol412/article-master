package com.nikol412.articlemaster.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nikol412.articlemaster.R
import com.nikol412.articlemaster.ui.overview.adapter.CollectionItem
import com.nikol412.articlemaster.ui.overview.adapter.CollectionsAdapter
import com.nikol412.articlemaster.ui.overview.adapter.OnCollectionClick

class OverviewFragment: Fragment() {

    private val viewModel by viewModels<OverviewViewModel>()

    private val adapter by lazy {
        CollectionsAdapter(object: OnCollectionClick {
            override fun onClick(item: CollectionItem) {
                //TODO implement navigation to description of collection
                Toast.makeText(requireContext(), item.content, Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        val recyclerVIew = view.findViewById<RecyclerView>(R.id.recycler_view_collections)

        recyclerVIew.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )

        recyclerVIew.adapter = adapter

        viewModel.collections.observe(viewLifecycleOwner) { collections ->
            adapter.setItems(collections)
        }

        return view
    }
}