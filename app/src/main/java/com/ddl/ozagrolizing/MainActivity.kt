package com.ddl.ozagrolizing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name: TextView = findViewById(R.id.Name)
        val inn: EditText = findViewById(R.id.Inn)
        val find: Button = findViewById(R.id.Find)
        find.setOnClickListener {
            val tinn = inn.text.toString().trim()
            //Тут должен быть запрос к базе данных с параметром tinn (текущий инн) и получить результат с наименованием контрагента или пустое значение скажем 0
            if(tinn == "0")
                name.text = "Контрагент не найден"
            else
                name.text = "Наименование контрагента"
        }
    }
}