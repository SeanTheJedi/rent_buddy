package com.rg.rentbuddy.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rg.rentbuddy.R
import com.rg.rentbuddy.adapters.RentalListAdpt
import com.rg.rentbuddy.adapters.SrchRentalAdapter
import com.rg.rentbuddy.classes.Rental
import com.rg.rentbuddy.databinding.FragmentListViewBinding
import com.rg.rentbuddy.repositories.RentalRepository
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListView.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListView : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var TAG = "List View"

    private lateinit var binding: FragmentListViewBinding
    private var rentalRepository: RentalRepository = RentalRepository()
    lateinit var adapter: RentalListAdpt
    var searchList:ArrayList<Rental> = ArrayList()

    lateinit var tvNoRentalMsg:TextView
    lateinit var recyclerView: RecyclerView

    var keyword:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            keyword = it.getString("keyword")
        }

        adapter = RentalListAdpt(searchList, true)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvNoRentalMsg = view.findViewById<TextView>(R.id.tv_no_rental_msg)
        recyclerView = view.findViewById<RecyclerView>(R.id.rv_rental_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        Log.d(TAG, "Passed keyword: $keyword")
        searchByList(this.keyword)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListView.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListView().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        searchByList(this.keyword)
    }

    fun searchByList(keyword: String?){
        Log.d(TAG, "Keyword to firebase: ${keyword}")

        lifecycleScope.launch{
            var searchResults = rentalRepository.searchRentals(keyword)
            Log.d(TAG, "search: $searchResults")
            if(searchResults.isNotEmpty()) {
                searchList.clear()
                searchList.addAll(searchResults)
                adapter.notifyDataSetChanged()
            } else {
                recyclerView.visibility = View.INVISIBLE
                tvNoRentalMsg.visibility = View.VISIBLE
            }
        }

    }
    fun addToShortlist(position:Int) { }
}