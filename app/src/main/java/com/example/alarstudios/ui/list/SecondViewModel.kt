package com.example.alarstudios.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.alarstudios.data.Repository
import com.example.alarstudios.data.api.ApiService
import com.example.alarstudios.data.paging_source.PlacePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    var code = ""

    val listData = Pager(PagingConfig(pageSize = 10)) {
        PlacePagingSource(
            apiService,
            code
        )
    }.liveData.cachedIn(viewModelScope)

}