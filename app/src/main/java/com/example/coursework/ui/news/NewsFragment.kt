package com.example.coursework.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.MainViewModel
import com.example.coursework.MainViewModelFactory
import com.example.coursework.R
import com.example.coursework.repository.Repository

class NewsFragment : Fragment() {

    private  lateinit var viewModel: MainViewModel

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

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPosts()
        viewModel.myPosts.observe(viewLifecycleOwner, Observer {
            it.body()?.result
            adapter.setData(it.body()?.result)
        })

        return root
    }
}