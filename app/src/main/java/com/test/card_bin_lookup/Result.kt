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
    val numberLength: Int? = null,
    @ColumnInfo(name = "number_luhn")
    val numberLuhn: Boolean? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    @ColumnInfo(name = "country_numeric")
    val countryNumeric: String? = null,
    @ColumnInfo(name = "country_alpha2")
    val countryAlpha2: String? = null,
    @ColumnInfo(name = "country_name")
    val countryName: String? = null,
    @ColumnInfo(name = "country_emoji")
    val countryEmoji: String? = null,
    @ColumnInfo(name = "country_currency")
    val countryCurrency: String? = null,
    @ColumnInfo(name = "country_latitude")
    val countryLatitude: Double? = null,
    @ColumnInfo(name = "country_longitude")
    val countryLongitude: Double? = null,
    @ColumnInfo(name = "bank_name")
    val bankName: String? = null,
    @ColumnInfo(name = "bank_url")
    val bankUrl: String? = null,
    @ColumnInfo(name = "bank_phone")
    val bankPhone: String? = null,
    @ColumnInfo(name = "bank_city")
    val bankCity: String? = null
)