package com.example.mobapp.data.models

import com.google.gson.annotations.SerializedName

data class BaseProductsResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("products") val products: List<Product>
)