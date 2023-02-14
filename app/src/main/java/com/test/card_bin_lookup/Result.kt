package com.test.card_bin_lookup

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var bin: Int = 0,
    @ColumnInfo(name = "number_length")
    val numberLength: Int? = 0,
    @ColumnInfo(name = "number_luhn")
    val numberLuhn: Boolean? = false,
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = false,
    @ColumnInfo(name = "country_numeric")
    val countryNumeric: String? = "",
    @ColumnInfo(name = "country_alpha2")
    val countryAlpha2: String? = "",
    @ColumnInfo(name = "country_name")
    val countryName: String? = "",
    @ColumnInfo(name = "country_emoji")
    val countryEmoji: String? = "",
    @ColumnInfo(name = "country_currency")
    val countryCurrency: String? = "",
    @ColumnInfo(name = "country_latitude")
    val countryLatitude: Double? = 0.0,
    @ColumnInfo(name = "country_longitude")
    val countryLongitude: Double? = 0.0,
    @ColumnInfo(name = "bank_name")
    val bankName: String? = "",
    @ColumnInfo(name = "bank_url")
    val bankUrl: String? = null,
    @ColumnInfo(name = "bank_phone")
    val bankPhone: String? = null,
    @ColumnInfo(name = "bank_city")
    val bankCity: String? = null
)