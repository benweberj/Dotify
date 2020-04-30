package com.benjweber.dotify.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.benjweber.dotify.OnSongClickListener
import com.benjweber.dotify.R
import com.benjweber.dotify.fragment.SongListFragment
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity.*

// UltimateMainActivity
class Activity: AppCompatActivity(), OnSongClickListener {
    override fun onCreate(savedInstanceBundle: Bundle?) {
        super.onCreate(savedInstanceBundle)
        setContentView(R.layout.activity)
        title = "Ultimate Male Activity"
        val songListFrag = SongListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragSongList, songListFrag)
            .commit()

        ibShuffle.setOnClickListener {
            songListFrag.
        }
    }

    override fun onSongClicked(song: Song) {
        TODO("Not yet implemented")
    }
}