package com.example.mobapp.ui

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobapp.R
import com.example.mobapp.data.models.Product
import com.example.mobapp.data.models.SalePrice
import com.example.mobapp.ui.productdetails.fragment.ProductDetailsFragment
import com.example.mobapp.utils.checkIfDisplayed
import com.example.mobapp.utils.checkIfTextDisplayed
import com.example.mobapp.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class SingleActivityTest {

    private companion object {
        const val ID = "someId"
        const val CATEGORY = "someCategory"
        const val NAME = "someName"
        const val URL = "/Bread.jpg"
        const val DESCRIPTION = "someDesc"
        const val AMOUNT = "200"
        const val CURRENCY = "EUR"
        const val AMOUNT_RESULT = "$AMOUNT $CURRENCY"
        
        private const val PRODUCT_TAG = "product"
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun testProductDetailsFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<ProductDetailsFragment> (bundleOf(PRODUCT_TAG to getProduct()), R.style.Theme_Mobapp) {
            Navigation.setViewNavController(this.view!!, navController)
        }
        verifyProductDetailsScreen()
    }

    private fun verifyProductDetailsScreen() {
        R.id.image.checkIfDisplayed()
        R.id.name_tv.run {
            checkIfDisplayed()
            NAME.checkIfTextDisplayed()
        }
        R.id.price_tv.run {
            checkIfDisplayed()
            AMOUNT_RESULT.checkIfTextDisplayed()
        }
    }

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