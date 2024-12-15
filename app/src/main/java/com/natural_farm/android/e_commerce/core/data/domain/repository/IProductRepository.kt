package com.natural_farm.android.e_commerce.core.data.domain.repository

import com.natural_farm.android.e_commerce.core.Resource
import com.natural_farm.android.e_commerce.core.data.domain.model.DetailProduct
import com.natural_farm.android.e_commerce.core.data.domain.model.Product

interface IProductRepository {
    fun getAllProducts(callback: (Resource<List<Product>>) -> Unit)
    fun getDetailProduct(productId: Int, callback: (Resource<DetailProduct>) -> Unit)

    fun getProductsByCategory(categoryName: String, callback: (Resource<List<Product>>) -> Unit)

    fun getProductsByLimit(limit : Int, callback: (Resource<List<Product>>) -> Unit)
    fun getProductsBySorting(sorting : String, callback: (Resource<List<Product>>) -> Unit)
}