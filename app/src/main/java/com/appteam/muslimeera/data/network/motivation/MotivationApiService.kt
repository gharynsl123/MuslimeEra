package com.appteam.muslimeera.data.network.motivation

import com.appteam.muslimeera.data.respone.MotivationResponseItem
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface MotivationApiService {
    @GET("motivation")
    fun getMotivation(): Flowable<List<MotivationResponseItem>>

}