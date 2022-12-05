package com.appteam.muslimeera.data.respone

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MotivationResponse(
	@field:SerializedName("MotivationResponse")
	val motivationResponse: List<MotivationResponseItem?>? = null
) : Parcelable

@Parcelize
data class MotivationResponseItem(

	@field:SerializedName("motavation")
	val motavation: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null
) : Parcelable
