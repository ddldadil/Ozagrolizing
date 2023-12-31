package com.ddl.ozagrolizing

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddl.ozagrolizing.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ContractAdapter.Listener {
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
            binding.message.visibility = TextView.GONE
            binding.imNoData.visibility = ImageView.GONE
            binding.Name.text = text[0].name
            binding.Region.text = text[0].region
            binding.District.text = text[0].district
            binding.rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ContractAdapter(this)
            binding.rcView.adapter = adapter
            adapter.submitList(text)
        }

        val loginInn = intent.getStringExtra("inn")
        lifecycleScope.launch{
            vm.getDataContract(loginInn.toString())
        }
    }

    override fun onClick(dContract: DataContract) {  //Нажатие на договор
        val sch = Intent(this, ScheduleActivity::class.java)
        val item = dContract.id.toString()
        sch.putExtra("item", item)
        startActivity(sch)
    }
}
