package com.appteam.muslimeera.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appteam.muslimeera.data.network.motivation.MotivationApiClient
import com.appteam.muslimeera.data.respone.MotivationResponseItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel: ViewModel() {
    val motiveResponse = MutableLiveData<List<MotivationResponseItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getMotivation(responseHandler : (List<MotivationResponseItem>) -> Unit, error: (Throwable) -> Unit){
        MotivationApiClient().getMotiveApiService().getMotivation().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe({
            responseHandler(it)
        }, {
            error(it)
        })
    }

    fun getDataMotive(){
        isLoading.value = true
        getMotivation({
            isLoading.value = false
            motiveResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }
}