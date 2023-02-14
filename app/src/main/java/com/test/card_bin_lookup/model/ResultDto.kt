package com.test.card_bin_lookup

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.card_bin_lookup.model.Result

@JsonClass(generateAdapter = true)
data class ResultDto(
    @Json(name = "number")
    val number: Number?,
    @Json(name = "scheme")
    val scheme: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "brand")
    val brand: String?,
    @Json(name = "prepaid")
    val prepaid: Boolean?,
    @Json(name = "country")
    val country: Country?,
    @Json(name = "bank")
    val bank: Bank?
)

fun ResultDto.toResult() = Result(
    numberLength = number?.length,
    numberLuhn = number?.luhn,
    scheme = scheme?.replaceFirstChar{it.uppercase()},
    type = type?.replaceFirstChar{it.uppercase()},
    brand = brand,
    prepaid = prepaid,
    countryNumeric = country?.numeric,
    countryAlpha2 = country?.alpha2,
    countryName = country?.name,
    countryEmoji = country?.emoji,
    countryCurrency = country?.currency,
    countryLatitude = country?.latitude,
    countryLongitude = country?.longitude,
    bankName = bank?.name,
    bankUrl = bank?.url,
    bankPhone = bank?.phone,
    bankCity = bank?.city
)

@JsonClass(generateAdapter = true)
data class Number(
    @Json(name = "length")
    val length: Int?,
    @Json(name = "luhn")
    val luhn: Boolean?,
)

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "numeric")
    val numeric: String?,
    @Json(name = "alpha2")
    val alpha2: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "emoji")
    val emoji: String?,
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "latitude")
    val latitude: Double?,
    @Json(name = "longitude")
    val longitude: Double?
)

@JsonClass(generateAdapter = true)
data class Bank(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "city")
    val city: String?
)