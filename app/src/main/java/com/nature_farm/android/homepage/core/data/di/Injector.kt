package com.nature_farm.android.homepage.core.data.di

import com.nature_farm.android.homepage.core.data.domain.usecase.ProductInteractor
import com.nature_farm.android.homepage.core.data.domain.usecase.ProductUseCase
import com.nature_farm.android.homepage.core.data.repository.ProductRepository
import com.nature_farm.android.homepage.core.data.source.remote.ProductRemoteDataSource
import com.nature_farm.android.homepage.core.data.source.remote.network.ApiService
import com.nature_farm.android.homepage.ui.ViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Injector {
    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()
    }

    private fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun provideProductRemoteDataSource(): ProductRemoteDataSource {
        return ProductRemoteDataSource.getInstance(provideApiService())
    }

    private fun provideProductRepository(): ProductRepository {
        return ProductRepository.getInstance(provideProductRemoteDataSource())
    }

    private fun provideProductInteractor(): ProductUseCase {
        return ProductInteractor(provideProductRepository())
    }

    fun provideViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(provideProductInteractor())
    }


}