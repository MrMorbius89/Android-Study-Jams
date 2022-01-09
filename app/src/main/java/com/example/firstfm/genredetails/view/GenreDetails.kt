package com.example.firstfm.genredetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import com.example.firstfm.adapter.ViewPagerAdapter
import com.example.firstfm.databinding.ActivityGenreDetailsBinding
import com.example.firstfm.genredetails.viewmodel.GenreDetailsViewModel
import com.example.firstfm.utils.Status
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreDetails : AppCompatActivity() {

    private lateinit var binding:ActivityGenreDetailsBinding

    private val genreDetailsViewModel:GenreDetailsViewModel by viewModels()

    lateinit var viewPagerAdapter: ViewPagerAdapter

    lateinit var tag:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tag = intent.getStringExtra("Genre")!!
        genreDetailsViewModel.tag = tag
        binding.tag.text = tag
        genreDetailsViewModel.genreInfoLiveData.observe(this, {
            if(it.status==Status.SUCCESS)
                binding.summary.text = Html.fromHtml(it.data!!.tag.wiki.summary, Html.FROM_HTML_MODE_LEGACY)
            else if(it.status == Status.ERROR )
                binding.summary.text = "Failed to load information"
        })

        viewPagerAdapter = ViewPagerAdapter(this)

        binding.viewpager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewpager){ tab, position ->
            val tabs = listOf("Artists", "Albums", "Tracks")
            tab.text = tabs[position]
            binding.viewpager.currentItem = position
        }.attach()

        binding.viewpager.currentItem = 0
    }
}