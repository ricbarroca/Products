package com.example.mobapp.ui.productdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.mobapp.data.models.Product
import com.example.mobapp.data.models.SalePrice
import com.example.mobapp.ui.productdetails.viewmodel.binding.ProductDetailsUiState
import com.example.mobapp.utils.InstantExecutorExtension
import com.example.mobapp.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(InstantExecutorExtension::class)
class ProductDetailsViewModelTest {

    private companion object {
        private const val PRODUCT_TAG = "product"

        const val ID = "someId"
        const val CATEGORY = "someCategory"
        const val NAME = "someName"
        const val URL = "/someUrl"
        const val DESCRIPTION = "someDesc"
        const val AMOUNT = "200"
        const val CURRENCY = "EUR"
    }


    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val savedStateHandle: SavedStateHandle = mock()

    private val viewModel by lazy { ProductDetailsViewModel(savedStateHandle) }

    @Test
    fun testScreenSuccessDataInSavedState() {
        val product = getMockProduct()

        whenever(savedStateHandle.get<Product>(PRODUCT_TAG)).thenReturn(product)

        viewModel.fetchProductDetails()

        assertThat(LiveDataTestUtil.getValue(viewModel.getUiObservable())).isInstanceOf(ProductDetailsUiState.PopulateUi::class.java)

    }

    @Test
    fun testScreenErrorData() {
        whenever(savedStateHandle.get<Product>(PRODUCT_TAG)).thenReturn(null)

        viewModel.fetchProductDetails()

        assertThat(LiveDataTestUtil.getValue(viewModel.getUiObservable())).isInstanceOf(ProductDetailsUiState.Error::class.java)
    }

    private fun getMockProduct() = Product(
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