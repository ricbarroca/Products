package com.example.mobapp.ui.productslist.provider

import com.example.mobapp.data.models.BaseProductsResponse
import com.example.mobapp.data.models.Product
import com.example.mobapp.data.models.SalePrice
import org.assertj.core.api.AssertionsForClassTypes.*
import org.junit.jupiter.api.Test

class ProductListProviderTest {

    private companion object {
        const val ID = "someId"
        const val CATEGORY = "someCategory"
        const val NAME = "someName"
        const val URL = "/someUrl"
        const val DESCRIPTION = "someDesc"
        const val AMOUNT = "200"
        const val CURRENCY = "EUR"
        const val TYPE_NAME = "someType"
        const val TYPE_DESC = "someTypeDesc"
        const val TYPE_ID = "someTypeId"
    }

    private val provider by lazy { ProductListProvider() }

    @Test
    fun testProvider() {
        val product = getProduct()
        val mockedItemList = getMockItemList(product)
        val mockResponseList = getMockProductBaseResponseList(product)

        assertThat(provider.get(mockResponseList)).isEqualTo(mockedItemList)
    }

    private fun getMockItemList(product: Product) = mutableListOf(
        ProductListItem.Header(TYPE_NAME), ProductListItem.ProductRow(product)
    )
    private fun getMockProductBaseResponseList(product: Product) = listOf(
        BaseProductsResponse(
            id = TYPE_ID,
            name = TYPE_NAME,
            description = TYPE_DESC,
            products = listOf(product)
        )
    )

    private fun getProduct() = Product(
        id = ID,
        categoryId = CATEGORY,
        name = NAME,
        url = URL,
        description = DESCRIPTION,
        salePrice = SalePrice(
            amount = AMOUNT,
            currency = CURRENCY
        )
    )
}