package com.rg.rentbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.rg.rentbuddy.adapters.SrchFragAdapter
import com.rg.rentbuddy.databinding.ActivitySearchResultsBinding

class SearchResults : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var adapter: SrchFragAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SrchFragAdapter(supportFragmentManager, lifecycle)

        binding.tbLayout.addTab(binding.tbLayout.newTab().setText("List"))
        binding.tbLayout.addTab(binding.tbLayout.newTab().setText("Map"))

        binding.vpTwo.adapter = adapter

        binding.tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.vpTwo.currentItem = tab.position
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.vpTwo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tbLayout.selectTab(binding.tbLayout.getTabAt(position))
            }
        })
    }
}