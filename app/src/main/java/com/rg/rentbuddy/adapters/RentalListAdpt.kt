package com.rg.rentbuddy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rg.rentbuddy.R
import com.rg.rentbuddy.classes.Rental

class RentalListAdpt (
    var rentals: MutableList<Rental>,
    var isHorizontal: Boolean
): RecyclerView.Adapter<RentalListAdpt.RentalListVH>() {

    inner class RentalListVH(itemView: View) : RecyclerView.ViewHolder (itemView) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentalListVH {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_rental_card, parent, false)
        return RentalListVH(view)
    }

    override fun getItemCount(): Int {
        return rentals.size
    }

    override fun onBindViewHolder(holder: RentalListVH, position: Int) {
        val currRental: Rental = rentals.get(position)

        val tvRentalAddress = holder.itemView.findViewById<TextView>(R.id.tv_property_address)
        val tvRentalCity = holder.itemView.findViewById<TextView>(R.id.tv_prop_city)
        val tvRentalRating = holder.itemView.findViewById<RatingBar>(R.id.rb_prop_ratings)
        val tvRentalType = holder.itemView.findViewById<TextView>(R.id.tv_prop_type)
        val tvRentalPrice = holder.itemView.findViewById<TextView>(R.id.tv_prop_price)

        tvRentalAddress.text = currRental.address
        tvRentalCity.text = currRental.city
        tvRentalRating.rating = currRental.rating.toFloat()
        tvRentalType.text =currRental.propertyType
        tvRentalPrice.text = currRental.price.toString()

    }
}