package com.example.yourcarsevice.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.yourcarsevice.*
import com.example.yourcarsevice.adpter.MyItemRecyclerViewAdapter
import com.example.yourcarsevice.model.retrofit.party.PartApiResponse
import com.example.yourcarsevice.model.room.Part
import com.example.yourcarsevice.viewmodel.PartItemFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val ADD_PART_REQUEST_CODE = 111
const val EDIT_PART_REQUEST_CODE = 222

class PartItemFragment : Fragment() {

    private lateinit var buttonSynchronization: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var partAdapter: MyItemRecyclerViewAdapter
    private var partsList: List<Part> = listOf()
    private var selectedPartId: Int? = 0

    private val partViewModel by viewModels<PartItemFragmentViewModel>()
    private val sharedPrefs by lazy {
        context?.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_party, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(context, AddEditActivity::class.java)
            startActivityForResult(intent, ADD_PART_REQUEST_CODE)
        }
        recyclerView = view.findViewById(R.id.recycler_view)

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
                val intent = Intent(context, AddEditActivity::class.java)
                selectedPartId = part.partId
                intent.putExtra(PART_ID, selectedPartId)
                intent.putExtra(PART_NAME, part.partName)
                intent.putExtra(PART_UPDATE, part.partUpdateDate)
                intent.putExtra(CAR_MILLAGE, part.carMillage)
                intent.putExtra(PART_PRICE, part.price)
                startActivityForResult(intent, EDIT_PART_REQUEST_CODE)
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
                partViewModel.deletePart(partToDelete)
            }
        }).attachToRecyclerView(recyclerView)
    }

    //todo fab onclick
    inner class PartItemFragmentClickHandlers {
        fun onFabClicked(view: View) {
            Toast.makeText(context, "onFabClick", Toast.LENGTH_SHORT).show()
            Log.i("onFabClick", "onFabClicked: onFabClick")
            val intent = Intent(context, AddEditActivity::class.java)
            startActivityForResult(intent, ADD_PART_REQUEST_CODE)
        }
    }

    private fun loadGenrePartsInList() {
        partViewModel.getParts().observe(viewLifecycleOwner, Observer {
            partsList = it
            fillRecyclerView()
        })
    }

    //todo result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("addParttoRoom", "onActivityResult: ok")
        if (requestCode == ADD_PART_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val part = Part()
            part.partName = data?.getStringExtra(PART_NAME)
            part.partUpdateDate = data?.getStringExtra(PART_UPDATE)
            part.carMillage = data?.getStringExtra(CAR_MILLAGE)
            part.price = data?.getStringExtra(PART_PRICE)
            partViewModel.addNewPart(part)
            val partApiResponse = PartApiResponse(
                selectedPartId,
                data?.getStringExtra(PART_NAME),
                data?.getStringExtra(PART_UPDATE),
                data?.getStringExtra(CAR_MILLAGE),
                "fff",
                data?.getStringExtra(PART_PRICE)
            )
            partViewModel.updateListResponse(partApiResponse)
        } else if (requestCode == EDIT_PART_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val part = Part()
            part.partId = selectedPartId
            part.partName = data?.getStringExtra(PART_NAME)
            part.partUpdateDate = data?.getStringExtra(PART_UPDATE)
            part.carMillage = data?.getStringExtra(CAR_MILLAGE)
            part.price = data?.getStringExtra(PART_PRICE)
            partViewModel.updatePart(part)
        }
    }
}