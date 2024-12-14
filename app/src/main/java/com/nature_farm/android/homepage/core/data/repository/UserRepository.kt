package com.nature_farm.android.homepage.core.data.repository

import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.User
import com.nature_farm.android.homepage.core.data.domain.repository.IUserRepository
import com.nature_farm.android.homepage.core.data.source.remote.ApiResponse
import com.nature_farm.android.homepage.core.data.source.remote.UserRemoteDataSource
import com.nature_farm.android.homepage.core.data.source.remote.response.UserResponse

class UserRepository private constructor(private val userRemoteDataSource: UserRemoteDataSource): IUserRepository {
    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(userRemoteDataSource: UserRemoteDataSource): UserRepository {
            synchronized(this) {
                INSTANCE = UserRepository(userRemoteDataSource)
            }
            return INSTANCE!!
        }
    }

  override  fun getUserProfile(userId: Int, callback: (Resource<User>) -> Unit) {
        userRemoteDataSource.getUserProfile(userId) { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    apiResponse.data.let {
                        val name = it.name?.firstname + " " + it.name?.lastname
                        val address = it.address?.street + ", " + it.address?.city
                        val user = User(
                            name = name,
                            email = it.email,
                            username = it.username,
                            address = address
                        )
                        callback(Resource.Success(user))
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