package com.example.mobapp.data.api

import com.example.mobapp.data.models.BaseProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET(".")
    suspend fun getProducts(): Response<List<BaseProductsResponse>>

}