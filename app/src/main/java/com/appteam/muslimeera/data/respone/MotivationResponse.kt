package com.appteam.muslimeera.data.respone

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MotivationResponseItem(

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable
