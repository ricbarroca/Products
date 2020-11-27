package com.example.mobapp.data.repository

import com.example.mobapp.core.connection.ResponseResult
import com.example.mobapp.data.datasource.ProductsDataSource
import com.example.mobapp.data.models.BaseProductsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productsDataSource: ProductsDataSource) {

    fun getProducts() : Flow<ResponseResult<List<BaseProductsResponse>>> = flow {
        emit(productsDataSource.getProducts())
    }.flowOn(Dispatchers.IO)
}