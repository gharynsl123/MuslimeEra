package com.appteam.muslimeera.data.network.hadits

import com.appteam.muslimeera.data.respone.AllBookResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface HaditsApiService {
    @GET("books")
    fun getAllBook(): Flowable<AllBookResponse>

    @GET("books/{id}?range=0-300")
    fun getTabs(
        @Path("id") id: String
    )

    @GET("books/{id}/{number}")
    fun getSpecific(
        @Path("id") id: String,
        @Path("number") number: Int
    )
}