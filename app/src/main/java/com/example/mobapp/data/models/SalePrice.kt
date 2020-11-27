package com.example.mobapp.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SalePrice(@SerializedName("amount") val amount: String,
                     @SerializedName("currency") val currency: String) : Parcelable