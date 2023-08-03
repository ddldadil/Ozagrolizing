package com.ddl.ozagrolizing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddl.ozagrolizing.databinding.ActivityScheduleBinding
import kotlinx.coroutines.launch

class ScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    private lateinit var vm: MainViewModel
    private lateinit var adapter: ScheduleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        vm.resultLiveSch.observe(this) {shc ->
            binding.rcViewSchedule.layoutManager = LinearLayoutManager(this@ScheduleActivity)
            adapter = ScheduleAdapter()
            binding.rcViewSchedule.adapter = adapter
            adapter.submitList(shc)
        }
        val item = intent.getStringExtra("item")
        lifecycleScope.launch{
            vm.getSchedule(item.toString())
        }
    }
}