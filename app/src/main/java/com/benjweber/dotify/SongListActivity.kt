package com.benjweber.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.benjweber.dotify.MainActivity.Companion.CURRENT_SONG
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*

// Song select screen
class  SongListActivity : AppCompatActivity() {
    private val rvSongs: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rvSongs) }
    private var selectedSong: Song? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        var library = SongDataProvider.getAllSongs()
        val songListAdapter = SongListAdapter(library)

        songListAdapter.onSongClicked = { song ->
            selectedSong = song
            if (song.title == "Thought Contagion") Toast.makeText(this, "Now that's a nice choice." ,Toast.LENGTH_SHORT).show()
            tvCurrentSong.text = "${song.title} - ${song.artist}"
        }
        rvSongs.adapter = songListAdapter

        miniPlayer.bringToFront() // Not sure if theres a way to do this in xml
        miniPlayer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(CURRENT_SONG, selectedSong)
            startActivity(intent)
        }

        ibShuffle.setOnClickListener {
            // Hacky way to do this but idk why I can't shuffle library directly.
            // Seems like its immutable, but idk why or how to fix that
            var newLib = mutableListOf<Song>()
            for (s in library) newLib.add(s)
            newLib.shuffle()
            songListAdapter.shuffleLibrary(newLib)
        }
    }
}