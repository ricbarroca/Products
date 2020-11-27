package com.example.mobapp.ui.productslist.viewmodel.binding

import com.example.mobapp.data.models.BaseProductsResponse
import com.example.mobapp.ui.productslist.provider.ProductListItem
import com.example.mobapp.ui.productslist.provider.ProductListProvider
import javax.inject.Inject

class ProductListUiModel(private val provider: ProductListProvider) {

    var products: List<BaseProductsResponse> = emptyList()

    val adapterList: List<ProductListItem>
        get() = provider.get(products)

}