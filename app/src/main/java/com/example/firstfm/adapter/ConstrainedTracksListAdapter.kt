package com.example.firstfm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstfm.databinding.ConstrainedRecyclerViewBinding
import com.example.firstfm.databinding.RecyclerViewBinding
import com.example.firstfm.tracks.model.retrofit.Tracks

class ConstrainedTracksListAdapter: RecyclerView.Adapter<ConstrainedTracksListAdapter.RecyclerViewHolder>() {

    private var dataList : List<Tracks> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ConstrainedRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
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

    fun setData(list:List<Tracks>){
        dataList = list
        notifyDataSetChanged()
    }
    class RecyclerViewHolder(val binding: ConstrainedRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)
}