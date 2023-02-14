package com.test.card_bin_lookup.model

import com.test.card_bin_lookup.ResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// REST-клиент
interface ApiService {
    // Запрос информации о данном BIN
    @GET("/{bin}")
    suspend fun lookupBin(@Path("bin") bin: Int): Response<ResultDto>
}