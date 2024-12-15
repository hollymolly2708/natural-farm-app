package com.nature_farm.android.homepage.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.nature_farm.android.homepage.adapter.DetailProductTabLayoutAdapter
import com.nature_farm.android.homepage.adapter.ProductAdapter
import com.nature_farm.android.homepage.core.data.di.Injector
import com.nature_farm.android.homepage.core.data.domain.model.Product
import com.nature_farm.android.homepage.databinding.ActivityDetailProductBinding
import com.nature_farm.android.homepage.ui.main.product.ProductViewModel

class DetailProductActivity : AppCompatActivity() {
    companion object {
        const val PRODUCT_ID = "product_id"
    }

    private lateinit var binding: ActivityDetailProductBinding
    private lateinit var viewmodel: ProductViewModel


    private var quantity: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val getIdProduct = intent.getIntExtra(PRODUCT_ID, 1)
        setupViewmodel()
        bindingTabLayout()
        getProductMaybeULike()
        setupObservers()
        getDetailProduct(getIdProduct)
        initializeView()
        quantityCount()


    }

    private fun setupObservers() {
        viewmodel.product.observe(this) {
            val adapter = ProductAdapter(it.shuffled())
            adapter.onItemClickCallback(object : ProductAdapter.OnItemClickCallback {
                override fun onClicked(data: Product) {
                    val intent =
                        Intent(this@DetailProductActivity, DetailProductActivity::class.java)
                    intent.putExtra(DetailProductActivity.PRODUCT_ID, data.id)
                    startActivity(intent)
                }
            })
            binding.rvMaybeULike.adapter = adapter
            binding.rvMaybeULike.layoutManager =
                GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)

            binding.rvMaybeULike.setHasFixedSize(true)
        }

        viewmodel.detailProduct.observe(this) {

            val price = it.priceAfterDiscount
            binding.tvInputNameProduct.text = it.productName
            binding.tvInputTotalPrice.text = "$ $price"
//            binding.tvInputTotalPrice.text = "$ ${price?.subtract(price.multiply(BigDecimal.valueOf(0.1)))}"
//            binding.tvInputDiscount.text = "10%"
            binding.tvInputCategory.text = it.category
            Log.e("detailProduct", it.productName.toString())
            val uri = Uri.parse(it.productImage)
            Glide.with(this).load(uri).skipMemoryCache(true).into(binding.ivProductImage)

        }

        viewmodel.loading.observe(this){
            setupLoading(it)
        }
    }

    private fun bindingTabLayout() {
        val detailProductTabLayoutAdapter = DetailProductTabLayoutAdapter(this)
        binding.viewpagerDetailProduct.adapter = detailProductTabLayoutAdapter

        val tabNames = arrayOf("Description", "Details")
        TabLayoutMediator(
            binding.tabLayoutDetailProduct,
            binding.viewpagerDetailProduct
        ) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    private fun initializeView(){
        binding.layoutParent.visibility = View.GONE
        binding.layoutBuyNow.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun setupLoading(loading : Boolean){

        if(loading){
            binding.layoutParent.visibility = View.GONE
            binding.layoutBuyNow.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.layoutParent.visibility = View.VISIBLE
            binding.layoutBuyNow.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }


    private fun getDetailProduct(productId: Int) {
        viewmodel.getDetailProduct(productId)
    }

    private fun getProductMaybeULike() {
        viewmodel.getAllProducts()
    }

    private fun quantityCount() {
        binding.tvInputQuantity.text = quantity.toString()
        binding.btnMinus.isEnabled = quantity > 0
        binding.btnPlus.setOnClickListener {
            quantity += 1
            binding.tvInputQuantity.text = quantity.toString()
            binding.btnMinus.isEnabled = true
        }
        binding.btnMinus.setOnClickListener {
            quantity = (quantity - 1).coerceAtLeast(0)
            binding.tvInputQuantity.text = quantity.toString()
            binding.btnMinus.isEnabled = quantity > 0
        }
    }


    private fun setupViewmodel() {
        val factory = Injector.provideViewModelFactory()
        viewmodel = ViewModelProvider(this, factory)[ProductViewModel::class.java]
    }


}