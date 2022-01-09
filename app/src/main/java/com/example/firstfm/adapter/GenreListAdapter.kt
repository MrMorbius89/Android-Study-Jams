package com.example.firstfm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstfm.databinding.GenreListViewBinding
import com.example.firstfm.genredetails.view.GenreDetails
import com.example.firstfm.main.model.retrofit.Genre
import java.util.*

class GenreListAdapter:RecyclerView.Adapter<GenreListAdapter.GenreListViewHolder>() {

    private var dataList : List<Genre> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreListViewHolder {
        val binding = GenreListViewBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener {
            parent.context.startActivity(Intent(parent.context, GenreDetails::class.java)
                .apply {
                    putExtra("Genre", binding.genreListItem.text)
                }) }
        return GenreListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreListViewHolder, position: Int) {
        holder.binding.genreListItem.text = dataList[position].name.trim().capitalize(Locale.ROOT)

    }

    override fun getItemCount(): Int {
     return dataList.size
    }

    fun setData(list:List<Genre>){
        dataList = list
        notifyDataSetChanged()
    }
    class GenreListViewHolder(val binding:GenreListViewBinding):RecyclerView.ViewHolder(binding.root)
}
