package com.nature_farm.android.homepage.ui.main.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.DetailProduct
import com.nature_farm.android.homepage.core.data.domain.model.Product
import com.nature_farm.android.homepage.core.data.domain.usecase.product.ProductUseCase

class ProductViewModel(private val productUseCase: ProductUseCase) : ViewModel() {
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    private val _detailProduct: MutableLiveData<DetailProduct> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()

    val detailProduct: LiveData<DetailProduct> = _detailProduct
    val loading: LiveData<Boolean> = _loading
    val product: LiveData<List<Product>> = _products

    fun getAllProducts() {
        productUseCase.getAllProducts { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _products.value = it

                    }
                    _loading.value = false
                }

                is Resource.Error -> {
                    resource.message?.let {

                    }
                    _loading.value = false
                }

                is Resource.Loading -> {
                    _loading.value = true
                }
            }
        }
    }

    fun getDetailProduct(productId: Int) {
        productUseCase.getDetailProduct(productId = productId) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _detailProduct.value = it
                    }
                    _loading.value = false
                }

                is Resource.Error -> {
                    resource.message?.let {

                    }
                    _loading.value = false
                }

                is Resource.Loading -> {
                    _loading.value = true
                }
            }
        }
    }

    fun getProductsByCategory(categoryName: String) {
        productUseCase.getProductsByCategory(categoryName) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _products.value = it
                    }
                }

                is Resource.Error -> {
                    resource.message?.let {

                    }
                }

                is Resource.Loading -> {

                }
            }
        }
    }

    fun getProductsByLimit(limit: Int) {
        productUseCase.getProductsByLimit(limit) { resource ->

            when (resource) {
                is Resource.Success -> {
                    resource.data.let {
                        _products.value = it
                    }
                }
                is Resource.Error -> {
                    resource.message?.let {

                    }
                }

                is Resource.Loading -> {

                }
            }
        }
    }

    fun getProductBySorting(sort: String) {
        productUseCase.getProductsBySorting(sort) { resource ->

            when (resource) {
                is Resource.Success -> {
                    resource.data.let {
                        _products.value = it
                    }
                }
                is Resource.Error -> {
                    resource.message?.let {

                    }
                }

                is Resource.Loading -> {

                }
            }
        }
    }
}