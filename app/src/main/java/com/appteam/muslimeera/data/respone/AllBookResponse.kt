package com.appteam.muslimeera.data.respone

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllBookResponse(

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    ) : Parcelable

@Parcelize
data class DataItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("available")
    val available: Int? = null,

    @field:SerializedName("id")
    val id: String? = null
) : Parcelable
