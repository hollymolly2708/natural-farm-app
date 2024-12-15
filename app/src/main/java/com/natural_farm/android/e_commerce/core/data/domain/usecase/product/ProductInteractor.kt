package com.natural_farm.android.e_commerce.core.data.domain.usecase.product

import com.natural_farm.android.e_commerce.core.Resource
import com.natural_farm.android.e_commerce.core.data.domain.model.DetailProduct
import com.natural_farm.android.e_commerce.core.data.domain.model.Product
import com.natural_farm.android.e_commerce.core.data.repository.ProductRepository

class ProductInteractor(private val productRepository: ProductRepository) : ProductUseCase {
    override fun getAllProducts(callback: (Resource<List<Product>>) -> Unit) {
        productRepository.getAllProducts(callback)
    }

    override fun getDetailProduct(productId: Int, callback: (Resource<DetailProduct>) -> Unit) {
        productRepository.getDetailProduct(productId, callback)
    }

    override fun getProductsByCategory(
        categoryName: String,
        callback: (Resource<List<Product>>) -> Unit,
    ) {
        productRepository.getProductsByCategory(categoryName, callback)
    }

    override fun getProductsByLimit(limit: Int, callback: (Resource<List<Product>>) -> Unit) {
        productRepository.getProductsByLimit(limit, callback)
    }

    override fun getProductsBySorting(
        sorting: String,
        callback: (Resource<List<Product>>) -> Unit,
    ) {
        productRepository.getProductsBySorting(sorting, callback)
    }
}