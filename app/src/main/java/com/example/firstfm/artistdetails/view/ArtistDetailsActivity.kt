package com.example.firstfm.artistdetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.firstfm.R
import com.example.firstfm.adapter.*
import com.example.firstfm.albums.model.retrofit.AlbumsList
import com.example.firstfm.artistdetails.model.retrofit.ArtistDetails
import com.example.firstfm.artistdetails.viewmodel.ArtistDetailsViewModel
import com.example.firstfm.databinding.ActivityArtistDetailsBinding
import com.example.firstfm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtistDetailsActivity : AppCompatActivity() {

    private val viewModel:ArtistDetailsViewModel by viewModels()

    private lateinit var binding:ActivityArtistDetailsBinding

    @Inject
    lateinit var tagListAdapter:TagListAdapter

    @Inject
    lateinit var albumsListAdapter: ConstrainedAlbumsListAdapter

    @Inject
    lateinit var tracksListAdapter: ConstrainedTracksListAdapter

    private lateinit var artist:String

    var details:ArtistDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        artist = intent.getStringExtra("Details")!!.trim()
        viewModel.artist = artist
        binding.topAlbums.adapter = albumsListAdapter
        binding.topTracks.adapter = tracksListAdapter
        binding.recyclerView.adapter = tagListAdapter
        binding.topAlbums.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.topTracks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.artistDetailsLiveData.observe(this, {
            if(it.status==Status.LOADING){
                binding.loader.visibility = View.VISIBLE
            } else if(it.status==Status.SUCCESS){
                binding.loader.visibility = View.GONE
                binding.artistName.text = it.data!!.name
                binding.listeners.text = it.data.stats.listeners+" Followers"
                binding.playcount.text = it.data.stats.listeners+" Playcount"
                binding.albumSummary.text = it.data.bio.summary
                tagListAdapter.setData(it.data.tags.tag.map { t -> t.name }.toList())
                Glide.with(binding.imageView).load(it.data.image[3].url).into(binding.imageView)
            } else {
                binding.loader.visibility = View.GONE
                binding.albumSummary.text = "Something went wrong"
                d("Details Error", it.failure.toString())
            }
        })

        viewModel.artistAlbumsLiveData.observe(this, {
            if(it.status==Status.SUCCESS){
                albumsListAdapter.setData(it.data!!.topalbums.album)
            } else if(it.status==Status.ERROR){
                d("Albums Error", it.failure.toString())
            }
        })

        viewModel.artistTracksLiveData.observe(this, {
            if(it.status==Status.SUCCESS){
                tracksListAdapter.setData(it.data!!.topTracks.track)
            } else if(it.status==Status.ERROR) {
                d("Tracks Error", it.failure.toString())
            }
        })

    }
}