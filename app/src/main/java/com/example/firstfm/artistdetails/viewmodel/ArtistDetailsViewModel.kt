package com.example.firstfm.artistdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.firstfm.artistdetails.model.ArtistDetailsRepository
import com.example.firstfm.artistdetails.model.retrofit.ArtistAlbums
import com.example.firstfm.artistdetails.model.retrofit.ArtistDetails
import com.example.firstfm.artistdetails.model.retrofit.ArtistTracks
import com.example.firstfm.utils.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ArtistDetailsViewModel @Inject constructor(private val artistsDetailsRepository: ArtistDetailsRepository):ViewModel() {

    lateinit var artist:String

    val artistDetailsLiveData:LiveData<Outcome<ArtistDetails>> by lazy {
        liveData(Dispatchers.IO) {
            emit(Outcome.loading<ArtistDetails>())
            try {
                emit(Outcome.success(artistsDetailsRepository.getArtistInfo(artist)))
            }catch (e:Throwable){
                emit(Outcome.error<ArtistDetails>(e))
            }
        }
    }

    val artistTracksLiveData:LiveData<Outcome<ArtistTracks>> by lazy {
        liveData(Dispatchers.IO) {
            emit(Outcome.loading<ArtistTracks>())
            try {
                emit(Outcome.success(artistsDetailsRepository.getArtistTracks(artist)))
            }catch (e:Throwable){
                emit(Outcome.error<ArtistTracks>(e))
            }
        }
    }

    val artistAlbumsLiveData:LiveData<Outcome<ArtistAlbums>> by lazy {
        liveData(Dispatchers.IO) {
            emit(Outcome.loading<ArtistAlbums>())
            try {
                emit(Outcome.success(artistsDetailsRepository.getArtistAlbums(artist)))
            }catch (e:Throwable){
                emit(Outcome.error<ArtistAlbums>(e))
            }
        }
    }

}