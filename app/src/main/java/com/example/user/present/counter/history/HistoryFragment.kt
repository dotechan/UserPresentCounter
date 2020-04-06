package com.example.user.present.counter.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.user.present.counter.R

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_history, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.history_recyclerview)
        val adapter = HistoryListAdapter(this.requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        // FIXME: HistoryViewModelの引数なしコンストラクタがないからcreateできないという警告が出る
        historyViewModel = ViewModelProvider
                .AndroidViewModelFactory(activity!!.application)
                .create(HistoryViewModel::class.java)
        historyViewModel.historyList.observe(this, Observer { historyList ->
            // TODO: UIの更新を行う
            historyList?.let { adapter.setHistoryList(it) }
        })

        return rootView
    }
}