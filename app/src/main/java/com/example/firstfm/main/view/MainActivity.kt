package com.example.firstfm.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstfm.adapter.GenreListAdapter
import com.example.firstfm.databinding.ActivityMainBinding
import com.example.firstfm.main.viewmodel.MainViewModel
import com.example.firstfm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel:MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var genreListAdapter:GenreListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.genreList.adapter = genreListAdapter
        binding.genreList.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        mainViewModel.genreLiveData.observe(this, {
            if (it.status==Status.LOADING){
                binding.loader.visibility = View.VISIBLE
                binding.error.visibility = View.GONE
                binding.showMore.visibility = View.GONE
                d("Status", "Loading")
            }
            else if (it.status == Status.SUCCESS) {
                binding.showMore.visibility = View.VISIBLE
                binding.loader.visibility = View.GONE
                binding.error.visibility = View.GONE
                genreListAdapter.setData(it.data!!)
                d("Data", it.data[0].name)
            } else{
                binding.showMore.visibility = View.GONE
                binding.loader.visibility = View.GONE
                binding.error.visibility = View.VISIBLE

                d("Status", it.failure!!.toString())
            }
        })

        binding.error.setOnClickListener {
            d("Refreshing", "Refreshing")
            mainViewModel.refresh()
        }

        binding.showMore.setOnClickListener {
            mainViewModel.expand()
            binding.showMore.text = mainViewModel.showMore
        }

    }
}