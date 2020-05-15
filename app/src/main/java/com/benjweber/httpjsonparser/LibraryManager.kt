package com.benjweber.httpjsonparser

import android.content.Context
import android.util.Log
import com.benjweber.httpjsonparser.model.Song

class LibraryManager(private val context: Context) {
    private lateinit var library: List<Song>
    private lateinit var currentSongInfo: Pair<Int, Song>
    private val apiManager = ApiManager(context)
    var onLibraryReadyListener: OnLibraryReadyListener? = null


    init {
        apiManager.fetchAllSongs {
            this.library = it
            onLibraryReadyListener?.onLibraryReady(it)
            currentSongInfo = Pair(0, it[0])
        }
    }

//    fun getLibrary(): List<Song> {
//        return this.library
//    }

//    private fun onSongsReady(songs: List<Song>) {
//        this.library = songs;
//        currentSongInfo = Pair(0, this.library[0])
//    }

    fun shuffle(): List<Song> {
        this.library = this.library.apply {
            toMutableList()
            shuffle()
            toList()
        }
        return this.library
    }

    fun nextSong() {
        val nextIndex = currentSongInfo.first+1.rem(library.size)
        this.currentSongInfo = Pair(nextIndex, library[nextIndex])
    }

    fun prevSong() {
        val nextIndex = currentSongInfo.first-1.rem(library.size)
        this.currentSongInfo = Pair(nextIndex, library[nextIndex])
    }
}