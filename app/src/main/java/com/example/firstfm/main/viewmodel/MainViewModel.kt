package com.example.firstfm.main.viewmodel

import android.util.Log.d
import androidx.lifecycle.*
import com.example.firstfm.main.model.MainRepository
import com.example.firstfm.main.model.retrofit.Genre
import com.example.firstfm.main.model.retrofit.TopGenres
import com.example.firstfm.utils.Outcome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {


    private val refreshLiveData:MutableLiveData<Outcome<List<Genre>>> = MutableLiveData()

    private var isExpanded:Boolean = false

    var showMore:String = "Show More"

    private lateinit var tagList:List<Genre>

    val genreLiveData:LiveData<Outcome<List<Genre>>> by lazy {
        liveData(Dispatchers.IO) {
            emit(Outcome.loading())
            try {
                tagList = mainRepository.getAllGenres().toptags.tag
                emit(Outcome.success(tagList.toMutableList().subList(0,9)))
            }catch (e:Throwable) {
                emit(Outcome.error<List<Genre>>(e))
            }finally {
                emitSource(refreshLiveData)
            }
        }
    }

    fun expand(){
        isExpanded=!isExpanded
        d("is Expanded", isExpanded.toString())
        if (isExpanded){
            showMore = "Show Less"
            refreshLiveData.postValue( Outcome.success(tagList))
        } else {
            showMore = "Show More"
            refreshLiveData.postValue(Outcome.success(tagList.toMutableList().subList(0,9)))
        }

    }

    fun refresh() {
        viewModelScope.launch (Dispatchers.IO){
            refreshLiveData.postValue(Outcome.loading())
            try {
                tagList = mainRepository.getAllGenres().toptags.tag
                refreshLiveData.postValue(Outcome.success(tagList.toMutableList().subList(0,9)))
            }catch (e:Throwable) {
                refreshLiveData.postValue(Outcome.error(e))
            }
        }
    }

}
