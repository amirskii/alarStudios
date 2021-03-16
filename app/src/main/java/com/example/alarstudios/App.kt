package com.example.alarstudios

import android.app.Application
import com.example.alarstudios.ui.details.DetailsFragment
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    private val YANDEX_MAP_KEY = "5b4e8af5-9694-4868-a0df-b988cc85d992"

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(YANDEX_MAP_KEY)

    }
}