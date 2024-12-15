package com.natural_farm.android.e_commerce.core.data.source.remote.network

import com.natural_farm.android.e_commerce.core.data.source.remote.response.ProductResponseItem
import com.natural_farm.android.e_commerce.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    fun getAllProducts(): Call<List<ProductResponseItem>>

    @GET("products/{productId}")
    fun getDetailProducts(@Path("productId") productId: Int): Call<ProductResponseItem>

    @GET("products/category/{categoryName}")
    fun getProductsByCategory(@Path("categoryName") categoryName: String): Call<List<ProductResponseItem>>

    @GET("products")
    fun getProductsByLimit(@Query("limit") limit: Int): Call<List<ProductResponseItem>>

    @GET("products")
    fun getProductsBySorting(@Query("sort") sort : String) : Call<List<ProductResponseItem>>

    @GET("users/{userId}")
    fun getUserProfile(@Path("userId") userId: Int): Call<UserResponse>


}