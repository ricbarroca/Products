package com.example.mobapp.ui.productdetails.viewmodel.binding

import com.example.mobapp.core.constants.BASE_URL
import com.example.mobapp.data.models.Product

class ProductDetailsUiModel {

    lateinit var product: Product

    val imageUrl: String
        get() = "$BASE_URL${product.url}"

    val name: String
        get() = product.name

    val price: String
        get() = "${product.salePrice.amount} ${product.salePrice.currency}"
}