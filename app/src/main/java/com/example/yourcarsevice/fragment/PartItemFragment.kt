package com.example.yourcarsevice.fragment

import android.content.Context
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
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.retrofitmvvm.service.RetrofitInstance
import com.example.yourcarsevice.R
import com.example.yourcarsevice.adpter.MyItemRecyclerViewAdapter
import com.example.yourcarsevice.model.retrofit.party.PartApiResponse
import com.example.yourcarsevice.model.retrofit.user.UserTokenResponse
import com.example.yourcarsevice.model.room.Part
import com.example.yourcarsevice.model.separatorForErrorMessenger
import com.example.yourcarsevice.viewmodel.PartItemFragmentViewModel
import com.example.yourcarsevice.viewmodel.UserViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartItemFragment : Fragment() {

    private lateinit var buttonSynchronization :Button
    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter: MyItemRecyclerViewAdapter
    private var partyList: List<Part> = listOf()
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

        recyclerView = view.findViewById(R.id.recycler_view)
        view.findViewById<Button>(R.id.button_synchronization).setOnClickListener {
           partViewModel.updateListResponse(PartApiResponse(2,"partName","partUpdateDate", 123,"comment" , 100))
        }
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