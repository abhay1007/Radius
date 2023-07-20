package com.example.radius.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.radius.R
import com.example.radius.model.FacilityOption


class FacilityOptionsAdapter(
    private val options: List<FacilityOption>,
) : RecyclerView.Adapter<FacilityOptionsAdapter.OptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {

        val option = options[position]

        holder.bind(option)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val optionNameTextView: TextView = itemView.findViewById(R.id.optionNameTextView)
        private val optionIconImageView: ImageView = itemView.findViewById(R.id.optionIconImageView)

        fun bind(option: FacilityOption) {
            optionNameTextView.text = option.name
            Log.d("taggy", option.icon)

//            // Load the icon using Glide library
            if (option.icon.equals("apartment")) {
                Glide.with(itemView)
                    .load(R.drawable.apartment)
                    .into(optionIconImageView)
            }
            if (option.icon.equals("condo")) {
                Glide.with(itemView)
                    .load(R.drawable.condo2x)
                    .into(optionIconImageView)
            }
            if (option.icon.equals("boat")) {
                Glide.with(itemView)
                    .load(R.drawable.boat2x)
                    .into(optionIconImageView)
            }
            if (option.icon.equals("land")) {
                Glide.with(itemView)
                    .load(R.drawable.land2x)
                    .into(optionIconImageView)
            }
            if (option.icon.equals("room")) {
                Glide.with(itemView)
                    .load(R.drawable.rooms2x)
                    .into(optionIconImageView)
            }
            if (option.icon.equals("no-room")) {
                Glide.with(itemView)
                    .load(R.drawable.noroom2x)
                    .into(optionIconImageView)
            }
            if (option.icon.equals("swimming")) {
                Glide.with(itemView)
                    .load(R.drawable.swimming2x)
                    .into(optionIconImageView)
            }
            if (option.icon.equals("garden")) {
                Glide.with(itemView)
                    .load(R.drawable.garage2x)
                    .into(optionIconImageView)
            }
            if (option.icon.equals("garage")) {
                Glide.with(itemView)
                    .load(R.drawable.garage2x)
                    .into(optionIconImageView)
            }


            // Set an OnClickListener to handle option selection

        }
    }
}
