package com.ddl.ozagrolizing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddl.ozagrolizing.databinding.ActivityScheduleBinding

class ScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    private lateinit var vm: MainViewModel
    private val adapter = ScheduleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcViewSchedule.setHasFixedSize(true)
        binding.rcViewSchedule.layoutManager = LinearLayoutManager(this)
        binding.rcViewSchedule.adapter = adapter

        vm = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]

        vm.resultLiveSch.observe(this) { schedules ->
            adapter.submitList(schedules)
        }

        val item = intent.getStringExtra(EXTRA_CONTRACT_ID).orEmpty()
        if (item.isNotEmpty()) {
            vm.loadSchedule(item)
        }
    }

    companion object {
        const val EXTRA_CONTRACT_ID = "item"
    }
}