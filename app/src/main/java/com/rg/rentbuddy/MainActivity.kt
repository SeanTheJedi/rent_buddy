package com.rg.rentbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.rg.rentbuddy.databinding.ActivityMainBinding
import org.checkerframework.checker.units.qual.t


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var TAG = "Main View or Home"

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener(this)
        binding.etSearch.editText?.doOnTextChanged { text, start, before, count ->
            binding.btnSearch.isEnabled = !text.isNullOrEmpty()
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_search -> {
                search()
            }
        }
    }

    fun search(){
        var intent = Intent(this, SearchResults::class.java)
        intent.putExtra("keyword", binding.etSearchField.text.toString())
        startActivity(intent)
    }


}