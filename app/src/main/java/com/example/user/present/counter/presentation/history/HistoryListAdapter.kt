package com.example.user.present.counter.presentation.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.user.present.counter.R
import com.example.user.present.counter.domain.history.History
import com.example.user.present.counter.domain.history.HistoryRecyclerViewList

class HistoryListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var historyRecyclerViewList = emptyList<HistoryListItem>()

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeIconView: ImageView? = itemView.findViewById(R.id.type_icon)
        val dateTextView: TextView? = itemView.findViewById(R.id.date_text)
        val headerTextView: TextView? = itemView.findViewById(R.id.header_text)
        val borderView: View? = itemView.findViewById(R.id.border_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
            HistoryViewHolder(inflater.inflate(getLayoutRes(viewType), parent, false))

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyRecyclerViewList[position]
        when (item) {
            is HistoryListItem.HeaderItem -> {
                holder.headerTextView?.text = item.label
            }
            is HistoryListItem.HistoryItem -> {
                holder.typeIconView?.setImageResource(item.history.type.resourceId)
                holder.dateTextView?.text = item.history.formatListContent()
            }
            is HistoryListItem.BorderItem -> {
                holder.borderView
            }
        }
    }

    override fun getItemCount() = historyRecyclerViewList.size

    override fun getItemViewType(position: Int): Int {
        return when (historyRecyclerViewList[position]) {
            is HistoryListItem.HeaderItem -> VIEW_TYPE_HEADER
            is HistoryListItem.HistoryItem -> VIEW_TYPE_HISTORY
            is HistoryListItem.BorderItem -> VIEW_TYPE_BORDER
        }
    }

    private fun getLayoutRes(viewType: Int) =
            when (viewType) {
                VIEW_TYPE_HEADER -> R.layout.item_header
                VIEW_TYPE_HISTORY -> R.layout.item_history
                VIEW_TYPE_BORDER -> R.layout.item_border
                else -> throw IllegalArgumentException("Unknown viewType $viewType")
            }

    internal fun setHistoryList(historyList: List<History>) {
        // TODO: 毎回リストを再作成していたらパフォーマンスが悪いので、リスト生成済みであれば追加するように修正する
        historyRecyclerViewList = HistoryRecyclerViewList(historyList).list
        // TODO: パフォーマンスチューニングする。必要なデータの更新だけ通知すればよい
        notifyDataSetChanged()
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_HISTORY = 2
        private const val VIEW_TYPE_BORDER = 3
    }
}
