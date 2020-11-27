package com.example.mobapp.ui.productslist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mobapp.core.connection.ErrorCode
import com.example.mobapp.core.connection.ResponseResult
import com.example.mobapp.data.models.BaseProductsResponse
import com.example.mobapp.data.repository.ProductsRepository
import com.example.mobapp.ui.productslist.provider.ProductListProvider
import com.example.mobapp.ui.productslist.viewmodel.binding.ProductListUiState
import com.example.mobapp.utils.CoroutinesTestExtension
import com.example.mobapp.utils.InstantExecutorExtension
import com.example.mobapp.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.AssertionsForClassTypes.*
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@ExtendWith(InstantExecutorExtension::class)
class ProductsListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    private val productsRepository: ProductsRepository = mock()

    private val provider: ProductListProvider = mock()

    private val viewModel by lazy { ProductsListViewModel(productsRepository, provider) }

    @Test
    fun testGetProductsSuccess() {
        val baseResponse = getMockProductBaseResponseList()

        runBlocking {
            whenever(productsRepository.getProducts()).thenReturn(
                flowOf(ResponseResult.Success(baseResponse))
            )
            assertThat(
                LiveDataTestUtil.getValue(viewModel.getUiObservable())
            ).isInstanceOf(ProductListUiState.PopulateUi::class.java)
        }
    }

    @Test
    fun testGetProductsError() {
        runBlocking {
            whenever(productsRepository.getProducts()).thenReturn(
                flowOf(ResponseResult.Error(ErrorCode.APP_UNEXPECTED_ERROR))
            )
            assertThat(
                LiveDataTestUtil.getValue(viewModel.getUiObservable())
            ).isInstanceOf(ProductListUiState.Error::class.java)
        }
    }

    private fun getMockProductBaseResponseList() = listOf(
        mock<BaseProductsResponse>()
    )
}