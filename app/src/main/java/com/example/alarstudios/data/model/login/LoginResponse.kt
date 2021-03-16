package com.example.alarstudios.data.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status")
    @Expose
    val status : String,

    @SerializedName("code")
    @Expose
    val code : String
)