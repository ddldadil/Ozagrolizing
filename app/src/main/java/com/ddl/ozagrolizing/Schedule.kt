package com.ddl.ozagrolizing

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "schedule", indices = [Index("contract_id")],
        foreignKeys = [
            ForeignKey(entity = Contract::class, parentColumns = ["id"], childColumns = ["contract_id"])
        ])
data class Schedule (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "contract_id")
    var contractId: Int,
    @ColumnInfo(name = "lineNumber")
    var lineNumber: Int,
    @ColumnInfo(name = "scheduleDate")
    var scheduleDate: String,
    @ColumnInfo(name = "remainder")
    var remainder: String,
    @ColumnInfo(name = "days")
    var days: String,
    @ColumnInfo(name = "mainDebt")
    var mainDebt: String,
    @ColumnInfo(name = "margin")
    var margin: String,
    @ColumnInfo(name = "total")
    var total: String,
    @ColumnInfo(name = "accrualFlag")
    var accrualFlag: String,
    @ColumnInfo(name = "paymentFlag")
    var paymentFlag: String)