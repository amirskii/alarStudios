package com.example.alarstudios.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.alarstudios.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: Repository
) : ViewModel() {

    fun login(username: String, password: String) = repository.login(username, password)
}