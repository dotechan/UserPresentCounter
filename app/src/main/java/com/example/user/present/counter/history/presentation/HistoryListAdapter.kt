package com.example.user.present.counter.history.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.user.present.counter.R
import com.example.user.present.counter.history.domain.History
import com.example.user.present.counter.history.domain.HistoryRecyclerViewList

class HistoryListAdapter internal constructor(
        private val context: Context
) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var historyRecyclerViewList = emptyList<HistoryListItem>()

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeIconView: ImageView? = itemView.findViewById(R.id.type_icon)
        val typeTextView: TextView? = itemView.findViewById(R.id.type_text)
        val dateTextView: TextView? = itemView.findViewById(R.id.date_text)
        val headerTextView: TextView? = itemView.findViewById(R.id.header_text)
        val borderView: View? = itemView.findViewById(R.id.border_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
            HistoryViewHolder(inflater
                    .inflate(HistoryListItem.getLayoutRes(viewType), parent, false))

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyRecyclerViewList[position]
        when (item) {
            is HistoryListItem.HeaderItem -> {
                holder.headerTextView?.text = item.label
            }
            is HistoryListItem.HistoryItem -> {
                holder.typeIconView?.setImageResource(item.history.type.resourceId)
                holder.typeTextView?.text = context.getString(item.history.type.historyTitleId)
                holder.dateTextView?.text = item.history.formatListContent()
            }
            is HistoryListItem.BorderItem -> {
                holder.borderView
            }
        }
    }

    override fun getItemCount() = historyRecyclerViewList.size

    override fun getItemViewType(position: Int): Int =
         historyRecyclerViewList[position].itemViewType

    internal fun setHistoryList(historyList: List<History>) {
        // TODO: 毎回リストを再作成していたらパフォーマンスが悪いので、リスト生成済みであれば追加するように修正する
        historyRecyclerViewList = HistoryRecyclerViewList(historyList).list
        // TODO: パフォーマンスチューニングする。必要なデータの更新だけ通知すればよい
        notifyDataSetChanged()
    }
}
