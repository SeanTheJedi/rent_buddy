package com.rg.rentbuddy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rg.rentbuddy.R
import com.rg.rentbuddy.classes.Rental

class SrchRentalAdapter (
    var rentals: MutableList<Rental>,
    val addToShortlistFunction:(Int) -> Unit
): RecyclerView.Adapter<SrchRentalAdapter.SearchListViewHolder>() {

    inner class SearchListViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView)  {
        init {
            var btnAddToShortlist = itemView.findViewById<Button>(R.id.btn_add_to_shortlist)
            btnAddToShortlist.setOnClickListener { addToShortlistFunction(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_search_rental_item, parent, false)
        return SearchListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rentals.size
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        val currProperty: Rental = rentals.get(position)

        val tvPropertyPrice = holder.itemView.findViewById<TextView>(R.id.tv_property_price)
        tvPropertyPrice.text = "${currProperty.price}"

        val tvPropertyType = holder.itemView.findViewById<TextView>(R.id.tv_property_type)
        tvPropertyType.text = "${currProperty.propertyType}"

//        val tvPropertySpecification = holder.itemView.findViewById<TextView>(R.id.tv_property_specification)
//        tvPropertySpecification.text = "${currProperty.specification}"

        val tvPropertyAddress = holder.itemView.findViewById<TextView>(R.id.tv_property_address)
        tvPropertyAddress.text = "${currProperty.address}"

    }
}