package com.ddl.ozagrolizing

import androidx.room.Dao
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT contract.id as id, kontragent.region, kontragent.district, kontragent.name, number, date, nomenclature.name as nomenclature, price, margin, term, prepayment, totalCost FROM contract\n" +
            "JOIN nomenclature ON contract.nomenclature_id=nomenclature.id\n" +
            "JOIN kontragent ON contract.kontragent_inn=kontragent.inn\n" +
            "WHERE inn=:inn")
    suspend fun getDataContract(inn : String): List<DataContract>

    @Query("SELECT * FROM schedule WHERE contract_id=:contractId")
    suspend fun getSchedule(contractId: String): List<Schedule>

}