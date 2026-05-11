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

class ContractAdapter(private val listener: Listener) : ListAdapter<DataContract, ContractAdapter.Holder>(Comparator()) {

    companion object {
        private val numberFormatter = NumberFormat.getInstance(Locale.FRENCH).apply {
            minimumFractionDigits = 2
        }
        private val percentFormatter = NumberFormat.getPercentInstance(Locale.getDefault()).apply {
            minimumFractionDigits = 0 // Вы можете настроить это значение по необходимости
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListContractBinding.bind(view)
        private val context = itemView.context // Получаем контекст один раз

        private fun formatCurrencyString(valueString: String): String? {
            return if (valueString.trim().isNotEmpty()) {
                try {
                    numberFormatter.format(valueString.replace(",", ".").toDouble())
                } catch (e: NumberFormatException) {
                    null
                }
            } else {
                null
            }
        }

        private fun formatPercentValue(valueString: String): String? {
            return if (valueString.trim().isNotEmpty()) {
                try {
                    val numericValue = valueString.replace(",", ".").toDouble()
                    percentFormatter.format(numericValue / 100.0)
                } catch (e: NumberFormatException) {
                    null
                }
            } else {
                null
            }
        }

        fun bind(item: DataContract, listener: Listener) = with(binding) {
            val valueNotAvailable = context.getString(R.string.contract_value_not_available)

            number.text = context.getString(R.string.contract_display_number, item.number)
            date.text = context.getString(R.string.contract_display_date, item.date)
            N.text = (adapterPosition + 1).toString()
            nomenclature.text = item.nomenclature

            val formattedPrice = formatCurrencyString(item.price)
            price.text = context.getString(R.string.contract_display_price, formattedPrice ?: valueNotAvailable)

            val formattedMargin = formatPercentValue(item.margin)
            margin.text = context.getString(R.string.contract_display_margin, formattedMargin ?: valueNotAvailable)

            term.text = context.getString(R.string.contract_display_term, item.term)
            
            val formattedPrepayment = formatPercentValue(item.prepayment)
            prepayment.text = context.getString(R.string.contract_display_prepayment, formattedPrepayment ?: valueNotAvailable)

            val formattedTotalCost = formatCurrencyString(item.totalCost)
            totalCost.text = context.getString(R.string.contract_display_total_cost, formattedTotalCost ?: valueNotAvailable)

            itemView.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<DataContract>() {
        override fun areItemsTheSame(oldItem: DataContract, newItem: DataContract): Boolean {
            // Предполагается, что у DataContract есть уникальное поле id.
            // Если нет, измените эту логику.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataContract, newItem: DataContract): Boolean {
            return oldItem == newItem // Для data class это сравнит все поля
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_contract, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface Listener {
        fun onClick(dContract: DataContract)
    }
}