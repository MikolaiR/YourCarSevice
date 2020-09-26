package com.example.yourcarsevice.adpter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.yourcarsevice.R
import com.example.yourcarsevice.databinding.FragmentItemRecyclerBinding
import com.example.yourcarsevice.model.room.Part

class MyItemRecyclerViewAdapter : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    var partsList = listOf<Part>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val fragmentItemBinding = DataBindingUtil.inflate<FragmentItemRecyclerBinding>(
            LayoutInflater.from(parent.context), R.layout.fragment_item_recycler, parent, false
        )
        return ViewHolder(fragmentItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val part = partsList[position]
        holder.fragmentItemBinding.part = part
    }

    override fun getItemCount(): Int = partsList.size

    inner class ViewHolder(val fragmentItemBinding: FragmentItemRecyclerBinding) : RecyclerView.ViewHolder(fragmentItemBinding.root) {
        init {
            fragmentItemBinding.root.setOnClickListener {
                val position = adapterPosition
                onItemClickListener.onItemClick(partsList[position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(part: Part)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}