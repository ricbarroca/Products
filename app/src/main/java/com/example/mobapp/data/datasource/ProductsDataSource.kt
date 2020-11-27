package com.example.mobapp.data.datasource

import com.example.mobapp.core.connection.BaseDataSource
import com.example.mobapp.core.connection.ConnectionManager
import com.example.mobapp.core.connection.ResponseResult
import com.example.mobapp.data.api.ProductsApi
import com.example.mobapp.data.models.BaseProductsResponse
import javax.inject.Inject

class ProductsDataSource @Inject constructor(
    connectionManager: ConnectionManager,
    private val productsApi: ProductsApi) : BaseDataSource(connectionManager) {

    suspend fun getProducts() : ResponseResult<List<BaseProductsResponse>> = getResult { productsApi.getProducts() }
}