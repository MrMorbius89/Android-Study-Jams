package com.example.firstfm.artists.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.firstfm.albums.model.retrofit.Albums
import com.example.firstfm.artists.model.ArtistsRepository
import com.example.firstfm.artists.model.retrofit.Artists
import com.example.firstfm.utils.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(private val artistsRepository: ArtistsRepository):ViewModel() {

    lateinit var tag:String

    val artistsLiveData: LiveData<Outcome<List<Artists>>> by lazy {
        liveData(Dispatchers.IO) {
            emit(Outcome.loading())
            try {
                emit(Outcome.success(artistsRepository.getAllArtists(tag).topartists.artist))
            }catch (e:Throwable) {
                emit(Outcome.error<List<Artists>>(e))
            }
        }
    }

}