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
            val inn = binding.loginInn.text.toString()
            if (!isEmpty()){
                i.putExtra("inn", inn)
                startActivity(i)
            }


        }
    }
    private fun isEmpty():Boolean{
        if (binding.loginInn.text.length<9) binding.loginInn.error = getString(R.string.error_inn)
        return binding.loginInn.text.length<9
    }
}