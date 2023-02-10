package com.minhaz_uddin.midtermproject.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minhaz_uddin.midtermproject.R
import com.minhaz_uddin.midtermproject.model.BookmarkArticle
import com.minhaz_uddin.midtermproject.model.Constants
import com.minhaz_uddin.midtermproject.viewModel.NewsViewModel

class BookmarkAdapter(private val context: Context,private val viewModel: NewsViewModel,private val bookmarkList:List<BookmarkArticle>)
    :RecyclerView.Adapter<BookmarkAdapter.BookmarkViewholder>() {

    class BookmarkViewholder(private val view:View):RecyclerView.ViewHolder(view){
        val title_book=view.findViewById<TextView>(R.id.title_book)
        val description_book=view.findViewById<TextView>(R.id.description_book)
        val author_book=view.findViewById<TextView>(R.id.author_book)
        val pub_date=view.findViewById<TextView>(R.id.date_publish_book)
        val newsImage=view.findViewById<ImageView>(R.id.news_image_book)
        val seeMore=view.findViewById<TextView>(R.id.seeMore_book)
        val delete=view.findViewById<ImageButton>(R.id.delete_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewholder {
        val adapterLayout=LayoutInflater.from(parent.context).inflate(R.layout.bookmark_list,parent,false)
        return BookmarkViewholder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BookmarkViewholder, position: Int) {
        val bookmarkNews=bookmarkList[position]
        holder.author_book.text=bookmarkNews.author
        holder.title_book.text=bookmarkNews.title
        holder.description_book.text=bookmarkNews.description
        Glide.with(context)
            .load(bookmarkNews.urlToImage)
            .centerCrop()
            .into(holder.newsImage)
        holder.seeMore.setOnClickListener {
            val bundle= Bundle()
            bundle.putString(Constants.NEWS_BASE,bookmarkNews.url)
            it.findNavController().navigate(R.id.detailNews,bundle)
        }
        holder.delete.setOnClickListener{
            viewModel.deleteBookmark(bookmarkNews)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }
}