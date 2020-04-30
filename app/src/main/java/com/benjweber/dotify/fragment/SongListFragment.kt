package com.benjweber.dotify.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.benjweber.dotify.R
import com.benjweber.dotify.SongListAdapter
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.fragment_song_list.*

// ListEmailFragment
class SongListFragment: Fragment() {
    private lateinit var songListAdapter: SongListAdapter
//    private val rvSongs: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rvSongs) }
//    private var selectedSong: Song? = null
    private var library: List<Song> =  SongDataProvider.getAllSongs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songListAdapter = SongListAdapter(library)

//        // Remove the song from this class's library so shuffling doesn't add them back
        songListAdapter.onSongLongClicked = { pos ->
            Toast.makeText(context, "\"${library[pos].title}\" deleted", Toast.LENGTH_SHORT).show()
            library = library.toMutableList().apply {
                removeAt(pos)
            }.toList()
        }
        rvSongs.adapter = songListAdapter


    }
}
