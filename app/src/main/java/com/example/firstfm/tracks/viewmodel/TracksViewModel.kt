package com.example.firstfm.tracks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.firstfm.albums.model.retrofit.Albums
import com.example.firstfm.tracks.model.TracksRepository
import com.example.firstfm.tracks.model.retrofit.Tracks
import com.example.firstfm.utils.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(private val tracksRepository: TracksRepository):ViewModel() {

    lateinit var tag:String

    val tracksLiveData: LiveData<Outcome<List<Tracks>>> by lazy {
        liveData(Dispatchers.IO) {
            emit(Outcome.loading())
            try {
                emit(Outcome.success(tracksRepository.getAllTracks(tag).tracks.track))
            }catch (e:Throwable) {
                emit(Outcome.error<List<Tracks>>(e))
            }
        }
    }
}