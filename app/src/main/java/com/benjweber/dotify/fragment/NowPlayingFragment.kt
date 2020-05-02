package com.benjweber.dotify.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.benjweber.dotify.R
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlin.random.Random

class NowPlayingFragment: Fragment() {
    private var playCount : Int = 0
    private var song: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                playCount = getInt("out_count")
                song = getParcelable("out_song")
            }
        } else {
            arguments?.let { args ->
                song = args.getParcelable("arg_song")
                playCount = Random.nextInt(100, 10000)
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateSong(song)

        // General player components
        tvPlayCount.text = "${playCount} plays"
        btnPlay.setOnClickListener { incrementPlay() }
        btnNext.setOnClickListener { announce("next") }
        btnPrev.setOnClickListener { announce("previous") }
    }

    fun updateSong(song: Song?) {
        song?.let {
            ivAlbumCover.setBackgroundResource(it.largeImageID)
            tvSongName.text = it.title
            tvArtist.text = it.artist
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putInt("out_count", playCount)
            putParcelable("out_song", song)
        }
        super.onSaveInstanceState(outState)
    }

    private fun incrementPlay() {
        playCount++
        tvPlayCount.text = "$playCount plays"
    }

    private fun announce(direction : String) {
        Toast.makeText(context, "Skipping to $direction track", Toast.LENGTH_SHORT).show()
    }
}
