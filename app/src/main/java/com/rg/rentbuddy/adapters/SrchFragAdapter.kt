package com.rg.rentbuddy.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rg.rentbuddy.fragments.ListView
import com.rg.rentbuddy.fragments.MapView

class SrchFragAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var keyword: String
) : FragmentStateAdapter (fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            var fragment = ListView()
            val args = Bundle()
            args.putString("keyword", keyword)
            fragment.arguments = args
            return fragment
        } else {
            return  MapView()
        }




    }

}