package com.rg.rentbuddy.classes

import java.io.Serializable
import java.util.UUID

data class Rental(
    var propertyType: String = "",
    var owner: String = "",
    var specification: MutableList<String> = mutableListOf(),
    var price: Double = 0.0,
    var description: String = "",
    var address: String = "",
    var city: String = "",
    var rating: Double = 1.0,
    var lat : Double = 0.0,
    var long: Double = 0.0,
    var isAvailable: Boolean = false,
//    var rentalID:String = UUID.randomUUID().toString()
): Serializable