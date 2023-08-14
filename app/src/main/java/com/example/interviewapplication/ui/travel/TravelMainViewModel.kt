package com.example.interviewapplication.ui.travel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.interviewapplication.remote.TravelInterface
import com.example.interviewapplication.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TravelMainViewModel @Inject constructor(
    private val travelInterface: TravelInterface
    ) : ViewModel() {

    private val query = MutableLiveData<Int>()
    val appTitle = MutableLiveData<String>()

    init {
        appTitle.postValue(Constants.title)
    }

    val list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = query)) {
            TravelPaging(travelInterface = travelInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setPage(page: Int){
        query.postValue(page)
    }

    fun setAppTitle(){
        appTitle.postValue(Constants.title)
    }
}