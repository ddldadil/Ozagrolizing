package com.ddl.ozagrolizing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddl.ozagrolizing.databinding.ListContractBinding
import java.text.NumberFormat
import java.util.Locale

class ContractAdapter(val listener: Listener) : ListAdapter<DataContract, ContractAdapter.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ListContractBinding.bind(view)
        var nf = NumberFormat.getInstance(Locale.FRENCH)
        fun bind(item: DataContract, listener: Listener) = with(binding){
            nf.minimumFractionDigits = 2
            number.text = "№ ${item.number} "
            date.text = "от ${item.date}"
            nomenclature.text = item.nomenclature
            if (item.price.trim().isNotEmpty()){
                price.text = "Цена: ${nf.format(item.price.replace(",",".").toDouble())}"
            }
            margin.text = "Маржа: ${item.margin}%"
            term.text = "Срок в (мес): ${item.term}"
            prepayment.text = "Аванс: ${item.prepayment}%"
            if (item.totalCost.trim().isNotEmpty()) {
                totalCost.text = "Общая лизинговая стоимость: ${nf.format(item.totalCost.replace(",",".").toDouble())}"
            }

            itemView.setOnClickListener{
                listener.onClick(item)
            }
        }
    }
    class Comparator : DiffUtil.ItemCallback<DataContract>(){
        override fun areItemsTheSame(oldItem: DataContract, newItem: DataContract): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataContract, newItem: DataContract): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_contract, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), listener)
    }
    interface Listener{
        fun onClick(dContract: DataContract)
    }
}