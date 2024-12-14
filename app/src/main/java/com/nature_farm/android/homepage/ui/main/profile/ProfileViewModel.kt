package com.nature_farm.android.homepage.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nature_farm.android.homepage.core.Resource
import com.nature_farm.android.homepage.core.data.domain.model.User
import com.nature_farm.android.homepage.core.data.domain.usecase.user.UserUseCase

class ProfileViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    fun getUserProfile(userId: Int) {
        userUseCase.getUserProfile(userId) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _user.value = it
                    }
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }
        }
    }
}