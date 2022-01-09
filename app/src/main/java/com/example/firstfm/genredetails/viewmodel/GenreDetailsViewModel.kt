package com.example.firstfm.genredetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.firstfm.genredetails.model.GenreDetailsRepository
import com.example.firstfm.genredetails.model.retrofit.GenreInfo
import com.example.firstfm.utils.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class GenreDetailsViewModel @Inject constructor(private val genreDetailsRepository: GenreDetailsRepository):ViewModel() {

    lateinit var tag:String


    val genreInfoLiveData: LiveData<Outcome<GenreInfo>> = liveData(Dispatchers.IO) {
        emit(Outcome.loading())
        try {
            emit(Outcome.success(genreDetailsRepository.getGenreInfo(tag)))
        }catch (e:Throwable) {
            emit(Outcome.error<GenreInfo>(e))
        }
    }

}