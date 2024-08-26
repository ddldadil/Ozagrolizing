package com.ddl.ozagrolizing

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddl.ozagrolizing.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Загружаем сохраненный логин, если он существует
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedInn = sharedPref.getString("inn", "")
        if (!savedInn.isNullOrEmpty()) {
            binding.inn.setText(savedInn)
        }

        binding.loginFind.setOnClickListener{

            val i = Intent(this, MainActivity::class.java)
            val inn = binding.inn.text.toString()
            if (!isEmpty()){
                i.putExtra("inn", inn)
                startActivity(i)
                with(sharedPref.edit()) {
                    putString("inn", inn)
                    apply()}
            }


        }
    }
    private fun isEmpty():Boolean{
        if (binding.inn.text.toString().length<9) binding.inn.error = getString(R.string.error_inn)
        return binding.inn.text.toString().length<9
    }
}