package com.benjweber.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.benjweber.dotify.MainActivity.Companion.CURRENT_SONG
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*

// Song select screen
class  SongListActivity : AppCompatActivity() {
    private val rvSongs: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rvSongs) }
    private var selectedSong: Song? = null
    private var library: List<Song> =  SongDataProvider.getAllSongs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"

        val songListAdapter = SongListAdapter(library)
        songListAdapter.onSongClicked = { song ->
            selectedSong = song
            if (song.title == "Thought Contagion") Toast.makeText(this, "Now that's a nice choice." ,Toast.LENGTH_SHORT).show()
            tvCurrentSong.text = "${song.title} - ${song.artist}"
        }
        // Remove the song from this class's library so shuffling doesn't add them back
        songListAdapter.onSongLongClicked = { pos ->
            Toast.makeText(this, "\"${library[pos].title}\" deleted", Toast.LENGTH_SHORT).show()
            library = library.toMutableList().apply {
                removeAt(pos)
            }.toList()
        }
        rvSongs.adapter = songListAdapter

        btnGoToAdd.setOnClickListener {
            startActivity(Intent(this, AddSongActivity::class.java))
        }

        miniPlayer.bringToFront()
        miniPlayer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(CURRENT_SONG, selectedSong)
            startActivity(intent)
        }

        ibShuffle.setOnClickListener {
            library = library.toMutableList().apply {
                shuffle()
            }.toList()
            songListAdapter.shuffleLibrary(library)
        }
    }
}