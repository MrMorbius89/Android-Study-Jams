package com.example.firstfm.albumdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.firstfm.albumdetails.model.AlbumsDetailsRepository
import com.example.firstfm.albumdetails.model.retrofit.AlbumDetails
import com.example.firstfm.utils.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AlbumsDetailsViewModel @Inject constructor(private val albumsDetailsRepository: AlbumsDetailsRepository):ViewModel() {

    lateinit var artist:String
    lateinit var album:String


    val albumDetailsLiveData:LiveData<Outcome<AlbumDetails>> by lazy {
        liveData (Dispatchers.IO){
            emit(Outcome.loading<AlbumDetails>())
            try {
                emit(Outcome.success(albumsDetailsRepository.getAlbumInfo(artist, album)))
            }catch (e:Throwable){
                emit(Outcome.error<AlbumDetails>(e))
            }
        }
    }

}
