package com.example.firstfm.artists.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstfm.adapter.ArtistsListAdapter
import com.example.firstfm.artists.viewmodel.ArtistsViewModel
import com.example.firstfm.databinding.FragmentArtistsBinding
import com.example.firstfm.genredetails.view.GenreDetails
import com.example.firstfm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtistsFragment : Fragment() {

    private lateinit var binding:FragmentArtistsBinding

    private val viewModel: ArtistsViewModel by viewModels()

    @Inject
    lateinit var artistListAdapter:ArtistsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtistsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tag = (activity as GenreDetails).tag
        binding.artistslist.adapter = artistListAdapter
        binding.artistslist.layoutManager = GridLayoutManager(context, 2)
        viewModel.artistsLiveData.observe(viewLifecycleOwner, {
            if(it.status== Status.LOADING){
                binding.loader.visibility = View.VISIBLE
                binding.error.visibility = View.GONE

            }else if(it.status== Status.SUCCESS){
                binding.loader.visibility = View.GONE
                binding.error.visibility = View.GONE
                artistListAdapter.setData(it.data!!)

            }else {
                binding.loader.visibility = View.GONE
                binding.error.visibility = View.VISIBLE
                binding.error.text = "Something went wrong"
                Log.d("Error", it.failure.toString())
            }
        })
    }
}