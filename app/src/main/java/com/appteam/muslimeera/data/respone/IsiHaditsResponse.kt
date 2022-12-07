package com.appteam.muslimeera.data.respone

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IsiHaditsResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("hadiths")
	val hadiths: List<HadithsItem?>? = null,

	@field:SerializedName("requested")
	val requested: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("available")
	val available: Int? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
data class HadithsItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("arab")
	val arab: String? = null
) : Parcelable
