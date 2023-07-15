package com.ddl.ozagrolizing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.ddl.ozagrolizing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = DataBase.getDataBase(this)
        binding.Find.setOnClickListener {
            db.getDao().getKontragent(binding.Inn.text.toString()).asLiveData().observe(this){list->
                list.forEach{
                    binding.Name.text = (it.name)
                }
            }

        }
    }
}