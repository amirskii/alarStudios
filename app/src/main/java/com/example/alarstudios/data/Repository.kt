package com.example.alarstudios.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.alarstudios.data.api.ApiService
import com.example.alarstudios.data.model.login.LoginResponse
import com.example.alarstudios.data.model.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {
    fun login(phone: String, password: String) = liveData<Resource<LoginResponse>>(Dispatchers.IO) {
        val liveData = MutableLiveData<Resource<LoginResponse>>()
        var resource = Resource.loading(null, null)
        liveData.postValue(resource)
        emitSource(liveData)

        try {
            val resp = apiService.login(phone, password)
            liveData.postValue(Resource.success(resp, null))
        } catch (e: Exception) {
            resource = Resource.error(e.localizedMessage, null, e)
            liveData.postValue(resource)
        }
    }

}