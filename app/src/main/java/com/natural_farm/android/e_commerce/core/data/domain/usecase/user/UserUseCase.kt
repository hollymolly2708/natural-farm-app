package com.natural_farm.android.e_commerce.core.data.domain.usecase.user

import com.natural_farm.android.e_commerce.core.Resource
import com.natural_farm.android.e_commerce.core.data.domain.model.User

interface UserUseCase {
    fun getUserProfile(userId: Int, callback: (Resource<User>) -> Unit)
}