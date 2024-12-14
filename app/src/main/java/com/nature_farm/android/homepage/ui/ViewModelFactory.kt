package com.nature_farm.android.homepage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nature_farm.android.homepage.core.data.domain.usecase.product.ProductUseCase
import com.nature_farm.android.homepage.core.data.domain.usecase.user.UserUseCase
import com.nature_farm.android.homepage.ui.main.product.ProductViewModel
import com.nature_farm.android.homepage.ui.main.profile.ProfileViewModel

class ViewModelFactory(
    private val productUseCase: ProductUseCase,
    private val userUseCase: UserUseCase,
) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(
            productUseCase: ProductUseCase,
            userUseCase: UserUseCase,
        ): ViewModelFactory {
            synchronized(this) {
                INSTANCE = ViewModelFactory(productUseCase, userUseCase)
            }
            return INSTANCE!!
        }

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> {
                return ProductViewModel(productUseCase) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                return ProfileViewModel(userUseCase) as T
            }

            else -> {
                throw Throwable("Unknown Viewmodel class : " + modelClass.name)
            }
        }
    }
}