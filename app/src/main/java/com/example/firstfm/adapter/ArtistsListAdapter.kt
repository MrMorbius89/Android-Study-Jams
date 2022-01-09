package com.example.firstfm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstfm.artistdetails.view.ArtistDetailsActivity
import com.example.firstfm.artists.model.retrofit.Artists
import com.example.firstfm.databinding.RecyclerViewBinding

class ArtistsListAdapter: RecyclerView.Adapter<ArtistsListAdapter.RecyclerViewHolder>() {

    private var dataList : List<Artists> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = RecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener {
            parent.context.startActivity(
                Intent(parent.context, ArtistDetailsActivity::class.java)
                .apply {
                    putExtra("Details", binding.title.text)
                }) }
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Glide.with(holder.binding.image).load(dataList[position].image[3].url).into(holder.binding.image)
        holder.binding.title.text = dataList[position].name
        holder.binding.subtitle.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list:List<Artists>){
        dataList = list
        notifyDataSetChanged()
    }
    class RecyclerViewHolder(val binding: RecyclerViewBinding): RecyclerView.ViewHolder(binding.root)
}