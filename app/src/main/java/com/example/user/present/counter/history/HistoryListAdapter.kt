package com.example.user.present.counter.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.user.present.counter.R

class HistoryListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var historyList = emptyList<History>()

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeIconView: ImageView = itemView.findViewById(R.id.type_icon)
        val dateTextView: TextView = itemView.findViewById(R.id.date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = inflater.inflate(R.layout.history_recyclerview_item,parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = historyList[position]
        holder.typeIconView.setImageResource(current.type.resourceId)
        holder.dateTextView.text = current.date.toString()
    }

    internal fun setHistoryList(historyList: List<History>) {
        this.historyList = historyList
        // TODO: パフォーマンスチューニングする。必要なデータの更新だけ通知すればよい
        notifyDataSetChanged()
    }

    override fun getItemCount() = historyList.size
}