package com.nature_farm.android.homepage.core.data.domain.usecase.user

import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.User
import javax.security.auth.callback.Callback

interface UserUseCase {
    fun getUserProfile(userId: Int, callback: (Resource<User>) -> Unit)
}