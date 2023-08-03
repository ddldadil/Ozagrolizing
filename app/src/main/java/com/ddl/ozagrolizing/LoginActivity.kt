package com.ddl.ozagrolizing

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

        binding.loginFind.setOnClickListener{

            val i = Intent(this, MainActivity::class.java)
            val inn = binding.inn.text.toString()
            if (!isEmpty()){
                i.putExtra("inn", inn)
                startActivity(i)
            }


        }
    }
    private fun isEmpty():Boolean{
        if (binding.inn.text.toString().length<9) binding.inn.error = getString(R.string.error_inn)
        return binding.inn.text.toString().length<9
    }
}