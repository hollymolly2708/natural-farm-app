package com.nature_farm.android.homepage.core.data.domain.usecase.product

import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.DetailProduct
import com.nature_farm.android.homepage.core.data.domain.model.Product

interface ProductUseCase {
    fun getAllProducts(callback: (Resource<List<Product>>) -> Unit)
    fun getDetailProduct(productId: Int, callback: (Resource<DetailProduct>) -> Unit)
}