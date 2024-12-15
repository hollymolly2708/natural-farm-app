package com.natural_farm.android.e_commerce.core.data.domain.usecase.user

import com.natural_farm.android.e_commerce.core.Resource
import com.natural_farm.android.e_commerce.core.data.domain.model.User
import com.natural_farm.android.e_commerce.core.data.repository.UserRepository

class UserInteractor(private val userRepository: UserRepository) : UserUseCase {
    override fun getUserProfile(userId: Int, callback: (Resource<User>) -> Unit) {
        userRepository.getUserProfile(userId, callback)
    }
}