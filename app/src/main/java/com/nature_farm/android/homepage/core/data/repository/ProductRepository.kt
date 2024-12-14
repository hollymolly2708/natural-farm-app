package com.nature_farm.android.homepage.core.data.repository

import com.airbnb.lottie.model.layer.NullLayer
import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.DetailProduct
import com.nature_farm.android.homepage.core.data.source.remote.ApiResponse
import com.nature_farm.android.homepage.core.data.source.remote.ProductRemoteDataSource
import com.nature_farm.android.homepage.core.data.domain.model.Product
import com.nature_farm.android.homepage.core.data.domain.repository.IProductRepository
import com.nature_farm.android.homepage.core.data.mapper.ProductMapper

class ProductRepository private constructor(private val productRemoteDataSource: ProductRemoteDataSource) :
    IProductRepository {
    companion object {
        @Volatile
        private var INSTANCE: ProductRepository? = null

        fun getInstance(productRemoteDataSource: ProductRemoteDataSource): ProductRepository {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = ProductRepository(productRemoteDataSource)
                }
            }
            return INSTANCE!!
        }
    }

    override fun getAllProducts(
        callback: (Resource<List<Product>>) -> Unit,
    ) {
        productRemoteDataSource.getAllProducts { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    apiResponse.data.let { productResponseItems ->
                        val products =
                            ProductMapper.productResponsesToProducts(productResponseItems)
                        callback(Resource.Success(products))
                    }
                }

                is ApiResponse.Error -> {
                    apiResponse.errorMessage.let {
                        callback(Resource.Error(it))
                    }
                }

                is ApiResponse.Empty -> {
                    callback(Resource.Error("Unknown Error"))
                }
            }
        }
    }

    override fun getDetailProduct(productId: Int, callback: (Resource<DetailProduct>) -> Unit) {
        productRemoteDataSource.getDetailProduct(productId) { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    apiResponse.data.let {
                        val detailProduct = ProductMapper.productResponseToDetailProduct(it)
                        callback(Resource.Success(detailProduct))
                    }
                }

                is ApiResponse.Error -> {
                    apiResponse.errorMessage.let {
                        callback(Resource.Error(it))
                    }
                }

                is ApiResponse.Empty -> {
                    callback(Resource.Error("Unknown error"))
                }
            }
        }
    }
}