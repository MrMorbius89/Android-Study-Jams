package com.example.firstfm.albumdetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.util.Log.d
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.firstfm.adapter.TagListAdapter
import com.example.firstfm.albumdetails.viewmodel.AlbumsDetailsViewModel
import com.example.firstfm.databinding.ActivityAlbumDetailsBinding
import com.example.firstfm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailsActivity : AppCompatActivity() {
    lateinit var name:String
    lateinit var artist:String

    private lateinit var binding:ActivityAlbumDetailsBinding

    private val viewModel:AlbumsDetailsViewModel by viewModels()

    @Inject
    lateinit var tagListAdapter: TagListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = intent.getStringExtra("Details")!!.trim()
        artist = intent.getStringExtra("Artist")!!.trim()
        viewModel.album = name
        viewModel.artist = artist
        binding = ActivityAlbumDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.albumSummary.movementMethod = ScrollingMovementMethod()
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = tagListAdapter
        viewModel.albumDetailsLiveData.observe(this, {
            if(it.status==Status.LOADING){
                binding.loader.visibility = View.VISIBLE
                binding.albumSummary.text = ""
            }else if(it.status==Status.SUCCESS){
                binding.loader.visibility = View.GONE
                tagListAdapter.setData(it.data!!.album.tags.tag.map { t -> t.name }.toList())
                Glide.with(binding.imageView).load(it.data.album.image[3].url).into(binding.imageView)
                binding.albumSummary.text = Html.fromHtml(it.data.album.wiki.summary, Html.FROM_HTML_MODE_LEGACY)
                binding.albumTitle.text = name
                binding.artist.text = "by "+artist
            }else {
                binding.loader.visibility = View.GONE
                d("Error", it.failure.toString())
                binding.albumSummary.text="Something went wrong"
            }
        })

    }
}