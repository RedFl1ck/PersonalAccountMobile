package com.example.coursework.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_news.view.*


class NewsListAdapter constructor(private val activity: Fragment, private val context: Context) : RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {

    private var newsList = emptyList<News>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_news, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.itemView.news_title_txt.text = currentItem.title
        holder.itemView.news_description_txt.text = currentItem.description
        holder.itemView.news_id_txt.text = currentItem.id.toString()

        /*holder.itemView.setOnClickListener {
            val action = CocktailsFragmentDirections.actionNavCocktailsToUpdateActivity(currentItem)
            holder.itemView.findNavController().navigate(action)
        }*/
    }

    fun setData(news: List<News>){
        this.newsList = news
        notifyDataSetChanged()
    }
}