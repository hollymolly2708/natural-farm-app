package com.nature_farm.android.homepage.core.data.source.remote.network

import com.nature_farm.android.homepage.core.data.source.remote.response.ProductResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    fun getAllProducts() : Call<List<ProductResponseItem>>

}