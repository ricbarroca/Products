package com.example.mobapp.ui.productslist.viewmodel.binding

import com.example.mobapp.data.models.BaseProductsResponse
import com.example.mobapp.ui.productslist.provider.ProductListItem
import com.example.mobapp.ui.productslist.provider.ProductListProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.AssertionsForClassTypes.*
import org.junit.jupiter.api.Test

class ProductListUiModelTest {

    private val provider: ProductListProvider = mock()

    private val uiModel by lazy { ProductListUiModel(provider) }

    @Test
    fun testModelData() {
        val mockedResponse = getMockProductBaseResponseList()
        val mockedTransformedList = getMockTransformedList()
        uiModel.products = mockedResponse
        whenever(provider.get(mockedResponse)).thenReturn(mockedTransformedList)
        assertThat(uiModel.adapterList).isEqualTo(mockedTransformedList)
    }

    private fun getMockProductBaseResponseList() = listOf(
        mock<BaseProductsResponse>()
    )

    private fun getMockTransformedList() = mutableListOf(
        mock<ProductListItem>()
    )
}