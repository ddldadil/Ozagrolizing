package com.ddl.ozagrolizing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddl.ozagrolizing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ContractAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private val adapter = ContractAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcView.setHasFixedSize(true)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter

        vm = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]

        vm.resultLive.observe(this) { contracts ->
            if (contracts.isNullOrEmpty()) return@observe
            binding.message.visibility = View.GONE
            binding.imNoData.visibility = View.GONE
            val first = contracts[0]
            binding.Name.text = first.name
            binding.Region.text = first.region
            binding.District.text = first.district
            adapter.submitList(contracts)
        }

        val loginInn = intent.getStringExtra(EXTRA_INN).orEmpty()
        if (loginInn.isNotEmpty()) {
            vm.loadContracts(loginInn)
        }
    }

    override fun onClick(dContract: DataContract) {
        val sch = Intent(this, ScheduleActivity::class.java).apply {
            putExtra(ScheduleActivity.EXTRA_CONTRACT_ID, dContract.id.toString())
        }
        startActivity(sch)
    }

    companion object {
        const val EXTRA_INN = "inn"
    }
}
