package com.example.user.present.counter.history.presentation

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
import com.example.user.present.counter.history.usecase.HistoryViewModel

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_history, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.history_recyclerview)
        val adapter = HistoryListAdapter(this.requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        historyViewModel = ViewModelProvider
                .AndroidViewModelFactory(activity!!.application)
                .create(HistoryViewModel::class.java)
        // 履歴リストの変更を監視する
        historyViewModel.historyList.observe(this, Observer { historyList ->
            historyList?.let { adapter.setHistoryList(it) }
        })

        return rootView
    }
}