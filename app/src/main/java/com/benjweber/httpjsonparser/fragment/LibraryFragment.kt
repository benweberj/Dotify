package com.benjweber.httpjsonparser.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.benjweber.httpjsonparser.*
import com.benjweber.httpjsonparser.model.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment: Fragment(), OnLibraryReadyListener {
    lateinit var libManager: LibraryManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val app = context.applicationContext as MusicApp
        libManager = app.libManager
        libManager.onLibraryReadyListener = this
        Log.i("bjw","onattach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("bjw","onCreate")
    }

    override fun onLibraryReady(library: List<Song>) {
        val libAdapter = LibraryAdapter(library, context?.applicationContext as MusicApp) // prolly don't do this
        rvSongs.adapter = libAdapter
        Log.i("bjw","onlibready")

//        ibShuffle.setOnClickListener {
////            libAdapter.shuffleLibrary(libManager.shuffle())
//            Log.i("bjw","onclick")
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.i("bjw", "${libManager.getLibrary()}")
//        val app = context?.applicationContext as MusicApp
//        app.apiManager.fetchAllSongs {
//            app.libManager.setLibrary(it)
//            val libAdapter = LibraryAdapter(it, app)
//            rvSongs.adapter = libAdapter
//        }


//        libAdapter.onSongClicked = { song -> onSongClickedListener?.onSongClicked(song) }

        // Remove the song from this class's library so shuffling doesn't add them back
//        songListAdapter.onSongLongClicked = { pos ->
//            val title = library[pos].title
//            val farewellMsg = if (title === "Thought Contagion") "Why?" else "$title deleted"
//
//            Toast.makeText(context, farewellMsg, Toast.LENGTH_SHORT).show()
//            library = library.toMutableList().apply {
//                removeAt(pos)
//            }.toList()
//        }

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//    }
}