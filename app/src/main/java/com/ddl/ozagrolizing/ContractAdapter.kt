package com.ddl.ozagrolizing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddl.ozagrolizing.databinding.ListContractBinding

class ContractAdapter : ListAdapter<DataContract, ContractAdapter.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ListContractBinding.bind(view)
        fun bind(item: DataContract) = with(binding){
            number.text = item.number
            date.text = item.date
            nomenclature.text = item.nomenclature
            price.text = item.price
            margin.text = item.margin
            term.text = item.term
            prepayment.text = item.prepayment
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
        holder.bind(getItem(position))
    }
}