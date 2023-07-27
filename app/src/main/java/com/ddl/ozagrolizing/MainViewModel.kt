package com.ddl.ozagrolizing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(val db: DataBase) : ViewModel() {

    var resultLive = MutableLiveData<List<DataContract>>()

    suspend fun getDataContract(inn: String) {
        resultLive.value = db.getDao().getDataContract(inn)
    }

}