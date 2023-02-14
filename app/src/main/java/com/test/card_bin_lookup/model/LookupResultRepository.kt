package com.test.card_bin_lookup.model

import android.util.Log
import com.test.card_bin_lookup.ResultDto
import retrofit2.Response

class LookupResultRepository {
    // Подключение ApiService
    private val apiService = NetworkModule.apiService

    suspend fun lookup(binToLookup: Int): Response<ResultDto>? {
        //Выполнение запроса
        try {
            return apiService.lookupBin(binToLookup)
        } catch (exception: Exception) {
            Log.i("Lookup failure", exception.message.toString())
        }
        return null
    }
}