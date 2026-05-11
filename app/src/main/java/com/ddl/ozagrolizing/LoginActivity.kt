package com.ddl.ozagrolizing

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddl.ozagrolizing.calc.LeasingCalculatorActivity
import com.ddl.ozagrolizing.databinding.ActivityLoginBinding

// Константы для SharedPreferences
private const val PREFS_NAME = "MyPrefs"
private const val KEY_INN = "inn"
private const val MIN_INN_LENGTH = 9

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding // Сделал приватной

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadSavedInn()

        binding.loginFind.setOnClickListener {
            handleLoginClick()
        }
        binding.btnOpenCalculator.setOnClickListener {
            startActivity(Intent(this, LeasingCalculatorActivity::class.java))
        }
    }

    private fun loadSavedInn() {
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedInn = sharedPref.getString(KEY_INN, "")
        if (!savedInn.isNullOrEmpty()) {
            binding.inn.setText(savedInn)
        }
    }

    private fun handleLoginClick() {
        val inn = binding.inn.text?.toString().orEmpty().trim()
        if (isInnValid(inn)) {
            val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            sharedPref.edit().putString(KEY_INN, inn).apply()

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(MainActivity.EXTRA_INN, inn)
            }
            startActivity(intent)
        }
    }

    private fun isInnValid(inn: String): Boolean {
        return if (inn.length < MIN_INN_LENGTH) {
            binding.inn.error = getString(R.string.error_inn_too_short) // Используем новую строку
            false
        } else {
            binding.inn.error = null // Убираем ошибку, если ИНН валиден
            true
        }
    }
}
