package com.nature_farm.android.homepage.core.data.source.remote

import android.util.Log
import com.nature_farm.android.homepage.core.data.source.remote.network.ApiService
import com.nature_farm.android.homepage.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var INSTANCE: UserRemoteDataSource? = null

        fun getInstance(apiService: ApiService): UserRemoteDataSource {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = UserRemoteDataSource(apiService)
                }
            }
            return INSTANCE!!
        }
    }

    fun getUserProfile(userId: Int, callback: (ApiResponse<UserResponse>) -> Unit) {
        apiService.getUserProfile(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
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

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("userProfile", t.message.toString())
                callback(ApiResponse.Error(t.message.toString()))
            }

        })
    }

}