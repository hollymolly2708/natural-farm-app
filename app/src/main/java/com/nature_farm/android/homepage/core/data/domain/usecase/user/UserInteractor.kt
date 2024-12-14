package com.nature_farm.android.homepage.core.data.domain.usecase.user

import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.User
import com.nature_farm.android.homepage.core.data.repository.UserRepository

class UserInteractor(private val userRepository: UserRepository) : UserUseCase {
    override fun getUserProfile(userId: Int, callback: (Resource<User>) -> Unit) {
        userRepository.getUserProfile(userId, callback)
    }
}