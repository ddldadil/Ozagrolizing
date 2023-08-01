package com.ddl.ozagrolizing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(val db: DataBase) : ViewModel() {

    var resultLive = MutableLiveData<List<DataContract>>()
    var resultLiveSch = MutableLiveData<List<Schedule>>()
    suspend fun getDataContract(inn: String) {
        if(db.getDao().getDataContract(inn).isNotEmpty()) {
           resultLive.value = db.getDao().getDataContract(inn)
        }
    }

    suspend fun getSchedule(id: String) {
        if(db.getDao().getSchedule(id).isNotEmpty()) {
            resultLiveSch.value = db.getDao().getSchedule(id)
        }
    }

}