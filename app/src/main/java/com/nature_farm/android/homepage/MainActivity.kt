package com.nature_farm.android.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nature_farm.android.homepage.databinding.ActivityMainBinding
import com.nature_farm.android.homepage.ui.main.order.OrderFragment

class MainActivity : AppCompatActivity() {
    private var selectedTab: Int = 1
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().setReorderingAllowed(true)
            .replace(R.id.fragment_container, HomeFragment(), null).commit()
        setupBottomBar()
    }


    private fun setupBottomBar() {
        binding.ivHome.setImageResource(R.drawable.ic_selected_home)
        binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
        binding.ivProduct.setImageResource(R.drawable.ic_product)
        binding.ivOrder.setImageResource(R.drawable.ic_bill)
        binding.ivProfile.setImageResource(R.drawable.ic_profile)
        binding.layoutHome.setOnClickListener {

            if (selectedTab != 1) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragment_container, HomeFragment(), null).commit()
                binding.ivHome.setImageResource(R.drawable.ic_selected_home)
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                binding.ivProduct.setImageResource(R.drawable.ic_product)
                binding.ivOrder.setImageResource(R.drawable.ic_bill)
                binding.ivProfile.setImageResource(R.drawable.ic_profile)
                selectedTab = 1
            }


        }
        binding.layoutProduct.setOnClickListener {
            if (selectedTab != 2) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragment_container, ProductFragment(), null).commit()
                binding.ivHome.setImageResource(R.drawable.ic_home)
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                binding.ivProduct.setImageResource(R.drawable.ic_selected_product)
                binding.ivOrder.setImageResource(R.drawable.ic_bill)
                binding.ivProfile.setImageResource(R.drawable.ic_profile)
                selectedTab = 2
            }
        }
        binding.layoutFavorite.setOnClickListener {
            if (selectedTab != 3) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragment_container, FavoriteFragment(), null).commit()
                binding.ivHome.setImageResource(R.drawable.ic_home)
                binding.ivFavorite.setImageResource(R.drawable.ic_selected_favorite)
                binding.ivProduct.setImageResource(R.drawable.ic_product)
                binding.ivOrder.setImageResource(R.drawable.ic_bill)
                binding.ivProfile.setImageResource(R.drawable.ic_profile)
                selectedTab = 3
            }
        }
        binding.layoutOrder.setOnClickListener {
            if (selectedTab != 4) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragment_container, OrderFragment(), null).commit()
                binding.ivHome.setImageResource(R.drawable.ic_home)
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                binding.ivProduct.setImageResource(R.drawable.ic_product)
                binding.ivOrder.setImageResource(R.drawable.ic_selected_bill)
                binding.ivProfile.setImageResource(R.drawable.ic_profile)
                selectedTab = 4
            }
        }
        binding.layoutProfile.setOnClickListener {
            if (selectedTab != 5) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragment_container, ProfileFragment(), null).commit()
                binding.ivHome.setImageResource(R.drawable.ic_home)
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite)
                binding.ivProduct.setImageResource(R.drawable.ic_product)
                binding.ivOrder.setImageResource(R.drawable.ic_bill)
                binding.ivProfile.setImageResource(R.drawable.ic_selected_profile)
                selectedTab = 5
            }
        }

    }
}