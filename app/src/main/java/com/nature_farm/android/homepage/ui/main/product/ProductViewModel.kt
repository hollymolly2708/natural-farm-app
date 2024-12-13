package com.nature_farm.android.homepage.ui.main.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.Product
import com.nature_farm.android.homepage.core.data.domain.usecase.ProductUseCase

class ProductViewModel(private val productUseCase: ProductUseCase) : ViewModel() {
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val product: LiveData<List<Product>> = _products

    fun getAllProducts() {
        productUseCase.getAllProducts { resource ->
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
}