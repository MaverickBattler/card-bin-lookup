package com.test.card_bin_lookup

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// REST-клиент
interface ApiService {
    // Запрос информации о данном BIN
    @GET("/{bin}")
    suspend fun lookupBin(@Path("bin") bin: Int): Response<ResultDto>
}