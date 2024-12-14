package com.nature_farm.android.homepage.core.data.domain.repository

import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.User

interface IUserRepository {
    fun getUserProfile(userId: Int, callback: (Resource<User>) -> Unit)
}