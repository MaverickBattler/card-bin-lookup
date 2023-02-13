package com.test.card_bin_lookup

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result")
data class Result(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var bin: Int = 0,
    @ColumnInfo(name = "number_length")
    var numberLength: Int = 0,
    @ColumnInfo(name = "number_luhn")
    var numberLuhn: Boolean = false,
    var scheme: String = "",
    var type: String = "",
    var brand: String = "",
    var prepaid: Boolean = false,
    @ColumnInfo(name = "country_numeric")
    var countryNumeric: String = "",
    @ColumnInfo(name = "country_alpha2")
    var countryAlpha2: String = "",
    @ColumnInfo(name = "country_name")
    var countryName: String = "",
    @ColumnInfo(name = "country_emoji")
    var countryEmoji: String = "",
    @ColumnInfo(name = "country_currency")
    var countryCurrency: String = "",
    @ColumnInfo(name = "country_latitude")
    var countryLatitude: Int = 0,
    @ColumnInfo(name = "country_longitude")
    var countryLongitude: Int = 0,
    @ColumnInfo(name = "bank_name")
    var bankName: String = "",
    @ColumnInfo(name = "bank_url")
    var bankUrl: String? = null,
    @ColumnInfo(name = "bank_phone")
    var bankPhone: String? = null,
    @ColumnInfo(name = "bank_city")
    var bankCity: String? = null
)