package com.example.alarstudios.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.alarstudios.R
import com.example.alarstudios.data.model.place.Place
import com.example.alarstudios.ui.details.DetailsFragment
import com.example.alarstudios.ui.list.SecondFragment
import com.example.alarstudios.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                findNavController(R.id.nav_host_fragment).popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
