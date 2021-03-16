package com.example.alarstudios.data.api

import com.example.alarstudios.data.model.login.LoginResponse
import com.example.alarstudios.data.model.place.Place
import com.example.alarstudios.data.model.place.PlacesResponse
import retrofit2.http.*


interface ApiService {
    @GET("test/auth.cgi")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): LoginResponse

    @GET("test/data.cgi")
    suspend fun getPlaces(
        @Query("code") code: String,
        @Query("p") page: Int
    ): PlacesResponse
}