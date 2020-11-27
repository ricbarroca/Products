package com.example.mobapp.ui.productslist.provider

import com.example.mobapp.data.models.Product
import com.example.mobapp.data.models.BaseProductsResponse

sealed class ProductListItem {

    data class Header(val title: String) : ProductListItem()
    data class ProductRow(val product: Product) : ProductListItem()
}

class ProductListProvider {

    fun get(products: List<BaseProductsResponse>) = mutableListOf<ProductListItem>().apply {
        products.forEach {
            add(ProductListItem.Header(it.name))
            it.products.forEach { prd -> add(ProductListItem.ProductRow(prd)) }
        }
    }
}