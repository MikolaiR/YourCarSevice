package com.example.yourcarsevice.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.yourcarsevice.R
import com.example.yourcarsevice.adpter.MyItemRecyclerViewAdapter
import com.example.yourcarsevice.model.room.Party

class PartItemFragment : Fragment() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter: MyItemRecyclerViewAdapter
    private var columnCount = 1
    private var partyList: List<Party> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_party, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        fillRecyclerView()
    }

    private fun fillRecyclerView() {
        adapter = MyItemRecyclerViewAdapter( partyList )
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = GridLayoutManager(requireContext().applicationContext, 1)
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireContext().applicationContext, 2)
        }
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}