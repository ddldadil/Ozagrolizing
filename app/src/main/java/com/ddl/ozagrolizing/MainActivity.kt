package com.ddl.ozagrolizing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ddl.ozagrolizing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val name: TextView = findViewById(R.id.Name)
        //val inn: EditText = findViewById(R.id.Inn)
        //val find: Button = findViewById(R.id.Find)
        val db = DataBase.getDataBase(this)
        binding.Find.setOnClickListener {
            val item1 = Kontragent(null, "Agrobank")
            val item2 = Kontragent(null, "Ozagrolizing")
            //val tinn = inn.text.toString().trim()
            Thread{
                db.getDao().insertKontragent(item2)
                db.getDao().insertKontragent(item2)
            }.start()
            //Тут должен быть запрос к базе данных с параметром tinn (текущий инн) и получить результат с наименованием контрагента или пустое значение скажем 0
            //if(tinn == "0")
            //    name.text = "Контрагент не найден"
            //else
            //    name.text = "Наименование контрагента"
        }
    }
}