package com.example.mobapp.di

import com.example.mobapp.core.connection.ConnectionManager
import com.example.mobapp.data.api.ProductsApi
import com.example.mobapp.data.datasource.ProductsDataSource
import com.example.mobapp.data.repository.ProductsRepository
import com.example.mobapp.ui.productslist.provider.ProductListProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProductsModule {

    @Provides
    @ActivityRetainedScoped
    fun provideProductsApi(retrofit: Retrofit): ProductsApi =
        retrofit.create(ProductsApi::class.java)

    @Provides
    @ActivityRetainedScoped
    fun provideProductsDataSource(
        connectionManager: ConnectionManager,
        productsApi: ProductsApi
    ): ProductsDataSource = ProductsDataSource(connectionManager, productsApi)

    @Provides
    @ActivityRetainedScoped
    fun provideProductsRepository(productsDataSource: ProductsDataSource): ProductsRepository =
        ProductsRepository(productsDataSource)

    @Provides
    @ActivityRetainedScoped
    fun provideProductListProvider(): ProductListProvider =
        ProductListProvider()
}