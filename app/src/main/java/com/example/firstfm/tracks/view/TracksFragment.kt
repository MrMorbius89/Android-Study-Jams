package com.example.firstfm.tracks.view

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstfm.R
import com.example.firstfm.adapter.TracksListAdapter
import com.example.firstfm.databinding.FragmentTracksBinding
import com.example.firstfm.genredetails.view.GenreDetails
import com.example.firstfm.tracks.viewmodel.TracksViewModel
import com.example.firstfm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TracksFragment : Fragment() {

    private lateinit var binding:FragmentTracksBinding
    private val viewModel:TracksViewModel by viewModels()

    @Inject
    lateinit var tracksListAdapter:TracksListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTracksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tag = (activity as GenreDetails).tag
        binding.trackslist.adapter = tracksListAdapter
        binding.trackslist.layoutManager = GridLayoutManager(context, 2)
        viewModel.tracksLiveData.observe(viewLifecycleOwner, {
            if(it.status== Status.LOADING){
                binding.loader.visibility = View.VISIBLE
                binding.error.visibility = View.GONE

            }else if(it.status== Status.SUCCESS){
                binding.loader.visibility = View.GONE
                binding.error.visibility = View.GONE
                tracksListAdapter.setData(it.data!!)

            }else {
                binding.loader.visibility = View.GONE
                binding.error.visibility = View.VISIBLE
                binding.error.text = "Something went wrong"
                d("Error", it.failure.toString())

            }
        })
    }
}
