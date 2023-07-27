package com.ddl.ozagrolizing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddl.ozagrolizing.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private lateinit var adapter: ContractAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        vm.resultLive.observe(this) { text ->
            binding.Name.text = text[0].name
            binding.Region.text = text[0].region
            binding.District.text = text[0].district
            binding.rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ContractAdapter()
            binding.rcView.adapter = adapter
            adapter.submitList(text)
        }

        binding.Find.setOnClickListener {
            lifecycleScope.launch{
                vm.getDataContract(binding.Inn.text.toString())
            }
        }
    }
}