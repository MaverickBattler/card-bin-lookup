package com.test.card_bin_lookup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.card_bin_lookup.model.ResultDao

class ResultViewModelFactory(private val dao: ResultDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LookupViewModel::class.java)) {
            return LookupViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}