package com.nature_farm.android.homepage.core.data.source.remote

import android.util.Log
import com.nature_farm.android.homepage.core.data.source.remote.network.ApiService
import com.nature_farm.android.homepage.core.data.source.remote.response.ProductResponse
import com.nature_farm.android.homepage.core.data.source.remote.response.ProductResponseItem
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
}