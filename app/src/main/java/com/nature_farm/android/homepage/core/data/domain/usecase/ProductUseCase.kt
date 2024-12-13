package com.nature_farm.android.homepage.core.data.domain.usecase

import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.Product

interface ProductUseCase {
    fun getAllProducts(callback: (Resource<List<Product>>) ->Unit)
}