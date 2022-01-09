package com.example.firstfm.albums.viewmodel

import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.firstfm.albums.model.AlbumsRepository
import com.example.firstfm.albums.model.retrofit.Albums
import com.example.firstfm.utils.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val albumsRepository: AlbumsRepository):ViewModel() {

    lateinit var tag:String

    val albumsLiveData:LiveData<Outcome<List<Albums>>> by lazy {
        liveData(Dispatchers.IO) {
            emit(Outcome.loading())
            try {
                emit(Outcome.success(albumsRepository.getAllAlbums(tag).albums.album))
            }catch (e:Throwable) {
                emit(Outcome.error<List<Albums>>(e))
            }
        }
    }
}