package com.natural_farm.android.e_commerce.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natural_farm.android.e_commerce.core.Resource
import com.natural_farm.android.e_commerce.core.data.domain.model.User
import com.natural_farm.android.e_commerce.core.data.domain.usecase.user.UserUseCase

class ProfileViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData()
    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    val user: LiveData<User> = _user
    val loading : LiveData<Boolean> = _loading

    fun getUserProfile(userId: Int) {
        userUseCase.getUserProfile(userId) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _user.value = it
                    }
                    _loading.value = false
                }

                is Resource.Error -> {
                    _loading.value = false
                }

                is Resource.Loading -> {
                    _loading.value = true
                }
            }
        }
    }
}