package com.example.user.present.counter.history.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.user.present.counter.R
import com.example.user.present.counter.history.domain.History
import com.example.user.present.counter.history.domain.HistoryRecyclerViewList
import timber.log.Timber

class HistoryListAdapter internal constructor(
        private val context: Context
) : ListAdapter<History, HistoryListAdapter.HistoryViewHolder>(DiffCallback()) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var historyRecyclerViewList = emptyList<HistoryListItem>()

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var typeIconView: ImageView? = itemView.findViewById(R.id.type_icon)
        private var typeTextView: TextView? = itemView.findViewById(R.id.type_text)
        private var dateTextView: TextView? = itemView.findViewById(R.id.date_text)
        private var headerTextView: TextView? = itemView.findViewById(R.id.header_text)
        private var borderView: View? = itemView.findViewById(R.id.border_view)

        fun bind(item: HistoryListItem) = with(itemView) {
            Timber.i("bind")
            when (item) {
                is HistoryListItem.HeaderItem -> {
                    headerTextView?.text = item.label
                }
                is HistoryListItem.HistoryItem -> {
                    typeIconView?.setImageResource(item.history.type.resourceId)
                    typeTextView?.text = context.getString(item.history.type.historyTitleId)
                    dateTextView?.text = item.history.formatListContent()
                }
                is HistoryListItem.BorderItem -> {
                    borderView
                }
            }
        }
    }

    // FIXME: 差分更新を実現したくてDiffUtilを実装したが、現状は呼ばれていない
    // リスト内にヘッダーのデータが含まれているケースでは、コンテンツとヘッダーでデータ構造が異なり
    // ユニークな値で比較することができない
    class DiffCallback : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            Timber.v("areItemsTheSame")
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            Timber.v("areContentsTheSame")
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        Timber.v("onCreateViewHolder")
        return HistoryViewHolder(inflater
                .inflate(HistoryListItem.getLayoutRes(viewType), parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        Timber.v("onBindViewHolder")
        val item = historyRecyclerViewList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        Timber.v("getItemCount")
        return historyRecyclerViewList.size
    }

    override fun getItemViewType(position: Int): Int {
        Timber.v("getItemViewType")
        return historyRecyclerViewList[position].itemViewType
    }

    internal fun setHistoryList(historyList: List<History>) {
        Timber.v("setHistoryList")
        historyRecyclerViewList = HistoryRecyclerViewList(historyList).list
        // TODO: パフォーマンスチューニングする。必要なデータの更新だけ通知すればよい
        notifyDataSetChanged()
    }
}
