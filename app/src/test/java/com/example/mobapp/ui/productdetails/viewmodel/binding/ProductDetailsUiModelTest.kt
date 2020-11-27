package com.example.mobapp.ui.productdetails.viewmodel.binding

import com.example.mobapp.core.constants.BASE_URL
import com.example.mobapp.data.models.Product
import com.example.mobapp.data.models.SalePrice
import org.assertj.core.api.AssertionsForClassTypes.*
import org.junit.jupiter.api.Test

class ProductDetailsUiModelTest {

    private companion object {
        const val ID = "someId"
        const val CATEGORY = "someCategory"
        const val NAME = "someName"
        const val URL = "/someUrl"
        const val DESCRIPTION = "someDesc"
        const val AMOUNT = "200"
        const val CURRENCY = "EUR"
        const val PRODUCT_URL_RESULT = "$BASE_URL$URL"
        const val AMOUNT_RESULT = "$AMOUNT $CURRENCY"
    }

    private val uiModel by lazy { ProductDetailsUiModel() }

    @Test
    fun testProductDetails() {
        uiModel.product = getMockProduct()

        assertThat(uiModel.name).isEqualTo(NAME)
        assertThat(uiModel.imageUrl).isEqualTo(PRODUCT_URL_RESULT)
        assertThat(uiModel.price).isEqualTo(AMOUNT_RESULT)
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