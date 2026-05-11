package com.ddl.ozagrolizing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddl.ozagrolizing.databinding.ListScheduleBinding
import java.text.NumberFormat
import java.util.Locale

class ScheduleAdapter : androidx.recyclerview.widget.ListAdapter<Schedule, ScheduleAdapter.Holder>(Comparator()) {

    companion object {
        private val numberFormatter = NumberFormat.getInstance(Locale.FRENCH).apply {
            minimumFractionDigits = 2
        }

        private fun safeFormat(value: String): String = try {
            numberFormatter.format(value.replace(",", ".").toDouble())
        } catch (e: NumberFormatException) {
            value
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListScheduleBinding.bind(view)
        fun bind(item: Schedule) = with(binding) {
            lineNumber.text = item.lineNumber.toString()
            scheduleDate.text = item.scheduleDate
            remainder.text = safeFormat(item.remainder)
            days.text = item.days
            mainDebt.text = safeFormat(item.mainDebt)
            marginSchedule.text = safeFormat(item.margin)
            total.text = safeFormat(item.total)
        }
    }

    class Comparator : DiffUtil.ItemCallback<Schedule>() {
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_schedule, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}