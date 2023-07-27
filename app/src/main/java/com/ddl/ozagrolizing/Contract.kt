package com.ddl.ozagrolizing

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "contract", indices = [Index("id"), Index("kontragent_inn"), Index("nomenclature_id")],
    foreignKeys = [
        ForeignKey(entity = Kontragent::class, parentColumns = ["inn"], childColumns = ["kontragent_inn"]),
        ForeignKey(entity = Nomenclature::class, parentColumns = ["id"], childColumns = ["nomenclature_id"])
    ])
data class Contract (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "number")
    var number: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "kontragent_inn")
    var kontragentInn: Int,
    @ColumnInfo(name = "nomenclature_id")
    var nomenclatureId: Int,
    @ColumnInfo(name = "price")
    var price: String,
    @ColumnInfo(name = "margin")
    var margin: String,
    @ColumnInfo(name = "term")
    var term: String,
    @ColumnInfo(name = "prepayment")
    var prepayment: String,
    @ColumnInfo(name = "dateOfIssue")
    var dateOfIssue: String,
    @ColumnInfo(name = "accrualScheme")
    var accrualScheme: String,
    @ColumnInfo(name = "reissued")
    var reissued: String,
    @ColumnInfo(name = "totalCost")
    var totalCost: String)