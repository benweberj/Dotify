package com.benjweber.dotify.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.benjweber.dotify.OnSongClickListener
import com.benjweber.dotify.R
import com.benjweber.dotify.fragment.NowPlayingFragment
import com.benjweber.dotify.fragment.SongListFragment
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity.*
import java.util.ArrayList

class Activity: AppCompatActivity(), OnSongClickListener {
    private var selectedSong: Song? = null
    private var library: List<Song> =  SongDataProvider.getAllSongs()

    override fun onCreate(savedInstanceBundle: Bundle?) {
        super.onCreate(savedInstanceBundle)
        setContentView(R.layout.activity)

        var songListFrag = getSongListFrag()
        if (savedInstanceBundle != null) {
            selectedSong = savedInstanceBundle.getParcelable("out_sel_song")
            tvCurrentSong.text = "${selectedSong?.title} - ${selectedSong?.artist}"
        } else {
            if (songListFrag == null) songListFrag = SongListFragment()

            songListFrag.arguments = Bundle().apply { putParcelableArrayList("arg_library", library as ArrayList) }
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, songListFrag, "songlist")
                .commit()
        }

        ibShuffle.setOnClickListener {
            songListFrag?.shuffleList()
        }

        miniPlayer.setOnClickListener {
            if (selectedSong != null) {
                val nowPlayingFrag = NowPlayingFragment()
                nowPlayingFrag.arguments = Bundle().apply { putParcelable("arg_song", selectedSong) }

                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, nowPlayingFrag, "nowplaying")
                    .addToBackStack("")
                    .commit()
            }
        }

        supportFragmentManager.addOnBackStackChangedListener { setNavigation() }
        setNavigation()
    }

    private fun setNavigation() {
        val hasBS = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(hasBS)

        if (hasBS) {
            title = "Now Playing"
            miniPlayer.visibility = View.INVISIBLE
        } else {
            title = "All Songs"
            miniPlayer.visibility = View.VISIBLE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("out_sel_song", selectedSong)
        super.onSaveInstanceState(outState)
    }

    private fun getNowPlayingFrag() = supportFragmentManager.findFragmentByTag("nowplaying") as NowPlayingFragment?
    private fun getSongListFrag() = supportFragmentManager.findFragmentByTag("songlist") as SongListFragment?

    override fun onSupportNavigateUp(): Boolean {
        val npFrag = getNowPlayingFrag()
        if (npFrag != null) {
            supportFragmentManager.popBackStack()
            return true
        }
        return super.onSupportNavigateUp()
    }

    override fun onSongClicked(song: Song) {
//        getNowPlayingFrag().let {
        getNowPlayingFrag()?.updateSong(song)

        selectedSong = song
        tvCurrentSong.text = "${song.title} - ${song.artist }"
        if (song.title == "Thought Contagion") Toast.makeText(this, "Now that's a nice choice." ,Toast.LENGTH_SHORT).show()
    }
}