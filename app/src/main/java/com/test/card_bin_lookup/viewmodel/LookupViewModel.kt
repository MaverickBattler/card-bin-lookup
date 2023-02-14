package com.test.card_bin_lookup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.card_bin_lookup.ResultDto
import com.test.card_bin_lookup.model.LookupResultRepository
import com.test.card_bin_lookup.model.ResultDao
import com.test.card_bin_lookup.toResult
import kotlinx.coroutines.launch
import retrofit2.Response

class LookupViewModel(private val dao: ResultDao) : ViewModel() {

    val lookupResults = dao.getAll()

    private val repository = LookupResultRepository()

    private val _lookupResponse = MutableLiveData<Response<ResultDto>?>()

    // Результат выполнения запроса
    val lookupResponse: LiveData<Response<ResultDto>?>
        get() = _lookupResponse

    fun lookup(binToLookup: String) {
        viewModelScope.launch {
            _lookupResponse.value = repository.lookup(binToLookup.toInt())
            val response = lookupResponse.value
            // Если не произошло ошибки получения данных
            if (response?.body() != null) {
                val resultDto = response.body()
                resultDto?.let {
                    // Сохранить его в базе данных
                    val result = resultDto.toResult()
                    // Установить BIN, который мы искали
                    result.bin = binToLookup.toInt()
                    dao.insert(result)
                }
            }
        }
    }

    // Проверка, имеет ли введенный BIN нужную длину
    fun isBinAppropriate(bin: String): Boolean {
        return bin.length in 6..8
    }
}