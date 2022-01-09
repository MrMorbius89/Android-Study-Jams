package com.example.firstfm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstfm.albumdetails.view.AlbumDetailsActivity
import com.example.firstfm.albums.model.retrofit.Albums
import com.example.firstfm.databinding.RecyclerViewBinding

class AlbumsListAdapter: RecyclerView.Adapter<AlbumsListAdapter.RecyclerViewHolder>() {

    private var dataList : List<Albums> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = RecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener {
            parent.context.startActivity(Intent(parent.context, AlbumDetailsActivity::class.java)
                    .apply {
                        putExtra("Details", binding.title.text)
                        putExtra("Artist", binding.subtitle.text.substring(2,binding.subtitle.text.length))
                    }) }
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Glide.with(holder.binding.image).load(dataList[position].image[3].url).into(holder.binding.image)
        holder.binding.title.text = dataList[position].name
        holder.binding.subtitle.text = "by ${dataList[position].artist.name}"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list:List<Albums>){
        dataList = list
        notifyDataSetChanged()
    }
    class RecyclerViewHolder(val binding: RecyclerViewBinding): RecyclerView.ViewHolder(binding.root)
}
