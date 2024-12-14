package com.nature_farm.android.homepage.core.data.source.remote.network

import com.nature_farm.android.homepage.core.data.source.remote.response.ProductResponseItem
import com.nature_farm.android.homepage.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    fun getAllProducts(): Call<List<ProductResponseItem>>

    @GET("products/{productId}")
    fun getDetailProducts(@Path("productId") productId: Int): Call<ProductResponseItem>

    @GET("users/{userId}")
    fun getUserProfile(@Path("userId") userId : Int ) : Call<UserResponse>

}