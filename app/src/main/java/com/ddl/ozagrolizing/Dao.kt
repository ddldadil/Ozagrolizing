package com.ddl.ozagrolizing

import android.content.Context
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertKontragent(kontragent: Kontragent)
    @Query("SELECT * FROM kontragent WHERE inn=:inn")
    fun getKontragent(inn : String): Flow<List<Kontragent>>
}