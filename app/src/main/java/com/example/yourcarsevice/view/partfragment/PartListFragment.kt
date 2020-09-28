package com.example.yourcarsevice.view.partfragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.yourcarsevice.R
import com.example.yourcarsevice.adpter.MyItemRecyclerViewAdapter
import com.example.yourcarsevice.databinding.FragmentPartListBinding
import com.example.yourcarsevice.models.room.Part
import com.example.yourcarsevice.viewmodel.PartListFragmentViewModel

class PartListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var partAdapter: MyItemRecyclerViewAdapter
    private var partsList: List<Part> = listOf()
    private val partViewModel by viewModels<PartListFragmentViewModel>()
    private lateinit var fragmentItemPartyBinding: FragmentPartListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentItemPartyBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_part_list,container,false)
        fragmentItemPartyBinding.clickHandlers = PartListFragmentClickHandlers()
        return fragmentItemPartyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = fragmentItemPartyBinding.recyclerView2
        fillRecyclerView()
        loadGenrePartsInList()
    }

    private fun fillRecyclerView() {
        partAdapter = MyItemRecyclerViewAdapter()
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = GridLayoutManager(requireContext().applicationContext, 1)
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireContext().applicationContext, 2)
        }
        recyclerView.itemAnimator = DefaultItemAnimator()
        partAdapter.partsList = partsList
        recyclerView.adapter = partAdapter

        partAdapter.setOnItemClickListener(object : MyItemRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(part: Part) {
                val backendId = part.backendId!!
                val action = PartListFragmentDirections.actionPartListFragmentToAddAndEditFragment(backendId)
                findNavController().navigate(action)
            }
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val partToDelete = partsList[viewHolder.adapterPosition]
                partToDelete.isDelete = true
                partViewModel.updatePart(partToDelete)
            }
        }).attachToRecyclerView(recyclerView)
    }

    private fun loadGenrePartsInList() {
        partViewModel.getParts().observe(viewLifecycleOwner, Observer {
            partViewModel.synchronization(it)
            val currentList = mutableListOf<Part>()
            for (part in it) {
                if (!part.isDelete) {
                    currentList.add(part)
                }
            }
            partsList = currentList
            fillRecyclerView()
        })
    }

    inner class PartListFragmentClickHandlers {
        fun onFabClicked(view: View) {
            Toast.makeText(context, "onFabClick", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_PartListFragment_to_AddAndEditFragment)
        }
    }
}
