package com.example.firstfm.albums.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstfm.R
import com.example.firstfm.adapter.AlbumsListAdapter
import com.example.firstfm.albums.viewmodel.AlbumsViewModel
import com.example.firstfm.databinding.FragmentAlbumsBinding
import com.example.firstfm.genredetails.view.GenreDetails
import com.example.firstfm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumsFragment : Fragment() {

    private lateinit var binding: FragmentAlbumsBinding
    private val viewModel:AlbumsViewModel by viewModels()

    @Inject lateinit var albumsListAdapter: AlbumsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tag = (activity as GenreDetails).tag
        binding.albumslist.adapter = albumsListAdapter
        binding.albumslist.layoutManager = GridLayoutManager(context, 2)
        viewModel.albumsLiveData.observe(viewLifecycleOwner, {
            if(it.status==Status.LOADING){
                binding.error.visibility = View.GONE
                binding.loader.visibility = View.VISIBLE

            }else if(it.status==Status.SUCCESS){
                binding.error.visibility = View.GONE
                binding.loader.visibility = View.GONE
                albumsListAdapter.setData(it.data!!)

            }else {
                binding.loader.visibility = View.GONE
                binding.error.visibility = View.VISIBLE
                binding.error.text = "Something went wrong"
                Log.d("Error", it.failure.toString())
            }
        })
    }

}