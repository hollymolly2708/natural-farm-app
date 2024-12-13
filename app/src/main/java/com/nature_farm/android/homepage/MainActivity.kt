package com.nature_farm.android.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nature_farm.android.homepage.databinding.ActivityMainBinding
import com.nature_farm.android.homepage.model.Article
import com.nature_farm.android.homepage.recyclerview.ArticleAdapter
import com.nature_farm.android.homepage.recyclerview.CategoryAdapter
import com.nature_farm.android.homepage.recyclerview.ExclusiveBrandAdapter
import com.nature_farm.android.homepage.recyclerview.HealthConditionAdapter
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
        setHealthConditionRecyclerView()
        setExclusiveBrandRecyclerView()
        setBestSellerRecyclerview()
        setArticleRecyclerview()
    }

    private fun setCategoryAdapter() {
        val adapter = CategoryAdapter(Data.categories())
        binding.rvCategories.adapter = adapter
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.setHasFixedSize(true)
    }

    private fun setFlashSaleRecyclerView() {
        val adapter = ProductAdapter(Data.products())
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducts.setHasFixedSize(true)
    }

    private fun setHealthConditionRecyclerView(){
        val adapter = HealthConditionAdapter(Data.healthConditions())
        binding.rvHealthCondition.adapter = adapter
        binding.rvHealthCondition.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHealthCondition.setHasFixedSize(true)
    }
    private fun setExclusiveBrandRecyclerView(){
        val adapter = ExclusiveBrandAdapter(Data.exclusiveBrand())
        binding.rvExclusiveBrand.adapter = adapter
        binding.rvExclusiveBrand.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvExclusiveBrand.setHasFixedSize(true)

    }

    private fun setBestSellerRecyclerview(){
        val adapter = ProductAdapter(Data.products())
        binding.rvBestSellerProducts.adapter = adapter
        binding.rvBestSellerProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvBestSellerProducts.setHasFixedSize(true)
    }

    private fun setArticleRecyclerview(){
        val adapter = ArticleAdapter(Data.article())
        binding.rvArticle.adapter = adapter
        binding.rvArticle.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvArticle.setHasFixedSize(true)
    }


}