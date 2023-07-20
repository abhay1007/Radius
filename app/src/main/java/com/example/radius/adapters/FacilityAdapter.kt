package com.example.radius.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radius.MainActivity
import com.example.radius.R
import com.example.radius.model.Facility
import io.reactivex.Observable

class FacilityAdapter(
    private val facilities: List<Facility>,
    private val context: Context,
) : RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_facility, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.bind(facility)
    }

    override fun getItemCount(): Int {
        return facilities.size
    }

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val facilityNameTextView: TextView = itemView.findViewById(R.id.facilityNameTextView)
        private val optionsRecyclerView: RecyclerView = itemView.findViewById(R.id.optionsRecyclerView)

        fun bind(facility: Facility) {
            facilityNameTextView.text = facility.name

            val optionsAdapter = FacilityOptionsAdapter(facility.options)

            optionsRecyclerView.layoutManager = LinearLayoutManager(context)

            optionsRecyclerView.adapter = optionsAdapter
        }
    }
}
