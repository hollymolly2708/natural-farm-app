package com.natural_farm.android.e_commerce.core.data.source.remote

import android.util.Log
import com.natural_farm.android.e_commerce.core.data.source.remote.network.ApiService
import com.natural_farm.android.e_commerce.core.data.source.remote.response.ProductResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        var INSTANCE: ProductRemoteDataSource? = null

        fun getInstance(apiService: ApiService): ProductRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = ProductRemoteDataSource(apiService)
                }
            }
            return INSTANCE!!
        }
    }

    fun getAllProducts(callback: (ApiResponse<List<ProductResponseItem>>) -> Unit) {
        apiService.getAllProducts().enqueue(object : Callback<List<ProductResponseItem>> {
            override fun onResponse(
                call: Call<List<ProductResponseItem>>,
                response: Response<List<ProductResponseItem>>,
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback(ApiResponse.Success(body))
                    } else {
                        callback(ApiResponse.Error("Response body is null"))
                    }
                } else {
                    callback(ApiResponse.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) {
                Log.e("allProducts", t.message.toString())
                callback(ApiResponse.Error(t.message.toString()))
            }

        })
    }

    fun getDetailProduct(productId: Int, callback: (ApiResponse<ProductResponseItem>) -> Unit) {
        apiService.getDetailProducts(productId).enqueue(object : Callback<ProductResponseItem> {
            override fun onResponse(
                call: Call<ProductResponseItem>,
                response: Response<ProductResponseItem>,
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback(ApiResponse.Success(body))
                    } else {
                        callback(ApiResponse.Error(response.message()))
                    }
                }
            }

            override fun onFailure(call: Call<ProductResponseItem>, t: Throwable) {
                Log.e("detailProduct", t.message.toString())
                callback(ApiResponse.Error(t.message.toString()))
            }

        })
    }

    fun getProducstByCategory(
        categoryName: String,
        callback: (ApiResponse<List<ProductResponseItem>>) -> Unit,
    ) {
        apiService.getProductsByCategory(categoryName)
            .enqueue(object : Callback<List<ProductResponseItem>> {
                override fun onResponse(
                    call: Call<List<ProductResponseItem>>,
                    response: Response<List<ProductResponseItem>>,
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            callback(ApiResponse.Success(body))
                        } else {
                            callback(ApiResponse.Error(response.message()))
                        }
                    } else {
                        callback(ApiResponse.Error(response.message()))
                    }
                }

                override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) {
                    Log.e("categoryProducts", t.message.toString())
                    callback(ApiResponse.Error(t.message.toString()))
                }

            })
    }

    fun getProductsByLimit(limit: Int, callback: (ApiResponse<List<ProductResponseItem>>) -> Unit) {
        apiService.getProductsByLimit(limit).enqueue(object : Callback<List<ProductResponseItem>> {
            override fun onResponse(
                call: Call<List<ProductResponseItem>>,
                response: Response<List<ProductResponseItem>>,
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback(ApiResponse.Success(body))
                    } else {
                        callback(ApiResponse.Error(response.message()))
                    }
                } else {
                    callback(ApiResponse.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) {
                Log.e("limitProducts", t.message.toString())
                callback(ApiResponse.Error(t.message.toString()))
            }

        })
    }

    fun getProductsBySorting(
        sort: String,
        callback: (ApiResponse<List<ProductResponseItem>>) -> Unit,
    ) {
        apiService.getProductsBySorting(sort).enqueue(object : Callback<List<ProductResponseItem>> {
            override fun onResponse(
                call: Call<List<ProductResponseItem>>,
                response: Response<List<ProductResponseItem>>,
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback(ApiResponse.Success(body))
                    } else {
                        callback(ApiResponse.Error(response.message()))
                    }
                } else {
                    callback(ApiResponse.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) {
                Log.e("sortProducts", t.message.toString())
                callback(ApiResponse.Error(t.message.toString()))
            }

        })
    }
}