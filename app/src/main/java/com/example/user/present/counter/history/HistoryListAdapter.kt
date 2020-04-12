package com.example.user.present.counter.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.user.present.counter.R
import java.util.*
import kotlin.collections.ArrayList

class HistoryListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var historyList = emptyList<History>()
    private var historyRecyclerViewList = emptyList<HistoryListItem>()

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeIconView: ImageView = itemView.findViewById(R.id.type_icon)
        val dateTextView: TextView = itemView.findViewById(R.id.date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
            HistoryViewHolder(inflater.inflate(getLayoutRes(viewType), parent, false))

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyRecyclerViewList[position]
        when (item) {
            is HistoryListItem.HeaderItem -> VIEW_TYPE_HEADER
            is HistoryListItem.HistoryItem -> {
                holder.typeIconView.setImageResource(item.history.type.resourceId)
                holder.dateTextView.text = item.history.formatHHmmss()
            }
            is HistoryListItem.BorderItem -> VIEW_TYPE_BORDER
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
        this.historyList = historyList
        // TODO: 毎回リストを再作成していたらパフォーマンスが悪いので、リスト生成済みであれば追加するように修正する
        historyRecyclerViewList = HistoryRecyclerViewList(historyList)
        // TODO: パフォーマンスチューニングする。必要なデータの更新だけ通知すればよい
        notifyDataSetChanged()
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_HISTORY = 2
        private const val VIEW_TYPE_BORDER = 3
    }

    inner class HistoryRecyclerViewList(
            private val historyList: List<History>
    ) : ArrayList<HistoryListItem>() {
        val historyRecyclerViewList = ArrayList<HistoryListItem>()

        init {
            // 履歴の日付が異なればHeaderを挿入する
            // 比較元の日付の初期値は協定世界時（UTC）で1970-01-01T00:00:00としておく
            val previousDate: Date = Date(0)

            historyList.forEach { history ->
                if (history.equalsByDate(previousDate)) {
                    historyRecyclerViewList.add(HistoryListItem.HeaderItem(history.formatMMdd()))
                    historyRecyclerViewList.add(HistoryListItem.BorderItem)
                }
                historyRecyclerViewList.add(HistoryListItem.HistoryItem(history))
            }
        }
    }
}
