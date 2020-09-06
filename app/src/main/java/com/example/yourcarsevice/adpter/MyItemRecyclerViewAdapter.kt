package com.example.yourcarsevice.adpter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.yourcarsevice.R
import com.example.yourcarsevice.model.room.Part

class MyItemRecyclerViewAdapter(
    private val values: List<Part>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textViewPartName.text = item.partName
        holder.textViewPartUpdateDate.text = item.partUpdateDate
        holder.textViewCarMillage.text = item.carMillage.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewPartName: TextView = view.findViewById(R.id.textView_part_name)
        val textViewPartUpdateDate: TextView = view.findViewById(R.id.textView_part_update_date)
        val textViewCarMillage: TextView = view.findViewById(R.id.textView_car_millage)
    }
}