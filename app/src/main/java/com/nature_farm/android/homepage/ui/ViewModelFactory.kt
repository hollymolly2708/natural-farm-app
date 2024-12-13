package com.nature_farm.android.homepage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nature_farm.android.homepage.core.data.domain.usecase.ProductUseCase
import com.nature_farm.android.homepage.ui.main.product.ProductViewModel

class ViewModelFactory(private val productUseCase: ProductUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(productUseCase: ProductUseCase): ViewModelFactory {
            synchronized(this) {
                INSTANCE = ViewModelFactory(productUseCase)
            }
            return INSTANCE!!
        }

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> {
                return ProductViewModel(productUseCase) as T
            }

            else -> {
                throw Throwable("Unknown Viewmodel class : " + modelClass.name)
            }
        }
    }
}