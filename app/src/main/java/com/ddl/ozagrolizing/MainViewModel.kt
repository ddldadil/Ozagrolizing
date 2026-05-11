package com.ddl.ozagrolizing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val db: DataBase) : ViewModel() {

    val resultLive = MutableLiveData<List<DataContract>>()
    val resultLiveSch = MutableLiveData<List<Schedule>>()

    fun loadContracts(inn: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = db.getDao().getDataContract(inn)
            if (data.isNotEmpty()) {
                resultLive.postValue(data)
            }
        }
    }

    fun loadSchedule(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = db.getDao().getSchedule(id)
            if (data.isNotEmpty()) {
                resultLiveSch.postValue(data)
            }
        }
    }
}