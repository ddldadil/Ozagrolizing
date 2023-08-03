package com.ddl.ozagrolizing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddl.ozagrolizing.databinding.ListScheduleBinding
import java.text.NumberFormat
import java.util.Locale

class ScheduleAdapter: androidx.recyclerview.widget.ListAdapter<Schedule, ScheduleAdapter.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListScheduleBinding.bind(view)
        var nf = NumberFormat.getInstance(Locale.FRENCH)
        fun bind(item: Schedule) = with(binding){
            nf.minimumFractionDigits = 2
            lineNumber.text = item.lineNumber.toString()
            scheduleDate.text = item.scheduleDate
            remainder.text = nf.format(item.remainder.replace(",",".").toDouble())
            days.text = item.days
            mainDebt.text = nf.format(item.mainDebt.replace(",",".").toDouble())
            marginSchedule.text = nf.format(item.margin.replace(",",".").toDouble())
            total.text = nf.format(item.total.replace(",",".").toDouble())
        }
    }
    class Comparator : DiffUtil.ItemCallback<Schedule>(){
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
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