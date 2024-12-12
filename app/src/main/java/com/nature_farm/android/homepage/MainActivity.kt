package com.nature_farm.android.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nature_farm.android.homepage.databinding.ActivityMainBinding
import com.nature_farm.android.homepage.recyclerview.CategoryAdapter
import com.nature_farm.android.homepage.recyclerview.ProductAdapter
import com.nature_farm.android.homepage.utils.Data

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCategoryAdapter()
        setFlashSaleRecyclerView()
    }

    fun setCategoryAdapter() {
        val adapter = CategoryAdapter(Data.categories())
        binding.rvCategories.adapter = adapter
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.setHasFixedSize(true)
    }

    fun setFlashSaleRecyclerView() {
        val adapter = ProductAdapter(Data.products())
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducts.setHasFixedSize(true)
    }
}