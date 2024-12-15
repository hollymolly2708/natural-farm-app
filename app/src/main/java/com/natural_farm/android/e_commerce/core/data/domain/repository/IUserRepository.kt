package com.natural_farm.android.e_commerce.core.data.domain.repository

import com.natural_farm.android.e_commerce.core.Resource
import com.natural_farm.android.e_commerce.core.data.domain.model.User

interface IUserRepository {
    fun getUserProfile(userId: Int, callback: (Resource<User>) -> Unit)
}