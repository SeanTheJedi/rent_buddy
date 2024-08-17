package com.rg.rentbuddy

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.google.android.material.tabs.TabLayout
import com.rg.rentbuddy.adapters.SrchFragAdapter
import com.rg.rentbuddy.databinding.ActivitySearchResultsBinding

class SearchResults : AppCompatActivity() {

    private var TAG = "SearchResults"
    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var adapter: SrchFragAdapter
    private var keyword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = this.intent

        if (intent != null) {
            keyword = intent.getStringExtra("keyword")!!
            Log.d(TAG, keyword)
        }

        binding.etSearch.editText?.setText(keyword)

        binding.etSearch.editText?.setOnEditorActionListener { v, actionId, event ->
            if ( actionId == EditorInfo.IME_ACTION_SEARCH ) {
                this.keyword = v.text.toString()
                adapter.notifyDataSetChanged()
                hideKeyboard()
            }
            true
        }

        adapter = SrchFragAdapter(supportFragmentManager, lifecycle, keyword)

        binding.tbLayout.addTab(binding.tbLayout.newTab().setText("List"))
        binding.tbLayout.addTab(binding.tbLayout.newTab().setText("Map"))

        binding.vpTwo.adapter = adapter

        binding.tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.vpTwo.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })

        binding.vpTwo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tbLayout.selectTab(binding.tbLayout.getTabAt(position))
            }
        })
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}