package com.ddl.ozagrolizing

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "kontragent")
data class Kontragent (
    @PrimaryKey(autoGenerate = true)
    var inn: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,)