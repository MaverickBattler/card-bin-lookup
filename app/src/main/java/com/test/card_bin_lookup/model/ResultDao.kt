package com.test.card_bin_lookup.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {
    @Insert
    suspend fun insert(result: Result)
    @Query("SELECT * FROM result ORDER BY id DESC")
    fun getAll(): LiveData<List<Result>>
}