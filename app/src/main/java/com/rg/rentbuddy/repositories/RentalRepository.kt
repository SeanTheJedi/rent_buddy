package com.rg.rentbuddy.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.firestore
import com.rg.rentbuddy.classes.Rental
import kotlinx.coroutines.tasks.await

class RentalRepository {

    private val TAG = "Rental Repository"
    val db = Firebase.firestore

    private val collection = "Rentals"

    var allRentals : MutableLiveData<List<Rental>> = MutableLiveData<List<Rental>>()

    suspend fun searchRentals(keyword: String?): ArrayList<Rental> {
        Log.d(TAG, "Fetching form firebase: ${keyword}")
        var searchResult: ArrayList<Rental> = ArrayList<Rental>()
        val rentalRef = db.collection(collection)

        if (keyword != null) {
            db.collection(collection)
                .whereGreaterThanOrEqualTo("city", keyword)
                .whereLessThan("city", keyword + 'z')
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                        var docData = document.toObject(Rental::class.java)
                        searchResult.add(docData)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }.await()
        }
        return searchResult
    }


}