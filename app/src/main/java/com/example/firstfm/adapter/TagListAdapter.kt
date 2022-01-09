package com.example.firstfm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstfm.databinding.AlbumTagListBinding
import com.example.firstfm.genredetails.view.GenreDetails
import java.util.*

class TagListAdapter: RecyclerView.Adapter<TagListAdapter.TagListViewHolder>() {

    private var dataList : List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagListViewHolder {
        val binding =AlbumTagListBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener {
            parent.context.startActivity(
                Intent(parent.context, GenreDetails::class.java)
                .apply {
                    putExtra("Genre", binding.albumTag.text)
                }) }
        return TagListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagListViewHolder, position: Int) {
        holder.binding.albumTag.text = dataList[position].trim().capitalize(Locale.ROOT)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list:List<String>){
        dataList = list
        notifyDataSetChanged()
    }
    class TagListViewHolder(val binding: AlbumTagListBinding): RecyclerView.ViewHolder(binding.root)
}
