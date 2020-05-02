package com.benjweber.dotify.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.benjweber.dotify.OnSongClickListener
import com.benjweber.dotify.R
import com.benjweber.dotify.SongListAdapter
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.fragment_song_list.*
import java.util.ArrayList
import kotlin.collections.*

class SongListFragment(): Fragment() {
    private lateinit var songListAdapter: SongListAdapter
    private lateinit var library: List<Song>
    private var onSongClickedListener: OnSongClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSongClickListener) onSongClickedListener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            library = savedInstanceState.getParcelableArrayList<Song>("out_library") as List<Song>
        } else {
            arguments?.let { args ->
                library = args.getParcelableArrayList<Song>("arg_library") as List<Song>
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("out_library", library as ArrayList<Song>)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songListAdapter = SongListAdapter(library)
        songListAdapter.onSongClicked = { song -> onSongClickedListener?.onSongClicked(song) }

        // Remove the song from this class's library so shuffling doesn't add them back
        songListAdapter.onSongLongClicked = { pos ->
            val title = library[pos].title
            val farewellMsg = if (title === "Thought Contagion") "Why?" else "$title deleted"

            Toast.makeText(context, farewellMsg, Toast.LENGTH_SHORT).show()
            library = library.toMutableList().apply {
                removeAt(pos)
            }.toList()
        }
        rvSongs.adapter = songListAdapter
    }

    fun shuffleList() {
        library = library.toMutableList().apply {
            shuffle()
        }.toList()
        songListAdapter.shuffleLibrary(library)
    }
}
