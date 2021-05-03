package com.example.coursework.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R

class NewsFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)

        // Recyclerview
        val adapter = NewsListAdapter(this@NewsFragment, this.requireContext())
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val list = listOf(News(1, "Заголовок1", "Описание1"),
            News(1, "Заголовок2", "Описание2"),
            News(1, "Заголовок3", "Описание3"),
            News(1, "Заголовок4", "Описание4"))
        adapter.setData(list)

        return root
    }
}