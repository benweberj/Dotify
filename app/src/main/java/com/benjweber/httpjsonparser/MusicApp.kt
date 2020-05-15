package com.benjweber.httpjsonparser

import android.app.Application
import android.util.Log
import android.widget.Toast

class MusicApp: Application() {
    lateinit var libManager: LibraryManager
    lateinit var apiManager: ApiManager

    override fun onCreate() {
        super.onCreate()
        libManager = LibraryManager(this)
        apiManager = ApiManager(this)
    }

    fun onSongsReady() {
//        Toast.makeText(this, "The songs are ready", Toast.LENGTH_SHORT).show()
    }
}