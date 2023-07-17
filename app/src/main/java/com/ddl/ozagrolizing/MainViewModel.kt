package com.ddl.ozagrolizing

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(val db: DataBase) : ViewModel() {

    var resultLive = MutableLiveData<String>()

    suspend fun getKontragent(inn: String) {
        resultLive.value = db.getDao().getKontragent(inn)
    }

}