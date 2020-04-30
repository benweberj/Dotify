//package com.benjweber.dotify
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import com.ericchee.songdataprovider.Song
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlin.random.Random
//
//// Song player screen
//class MainActivity : AppCompatActivity() {
//    private var playCount : Int = Random.nextInt(100, 10000)
//    private val adjectives = listOf("Bent", "Weird", "Toasty", "Irregular", "Quantum", "Springloaded", "Spicy")
//    private val nouns = listOf("Rectangle", "Mama", "Program", "Bean", "Meme", "Human")
//    lateinit var currentSong: Song
//
//    companion object {
//        const val CURRENT_SONG = "CURRENT_SONG"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Song-specific updates
//        currentSong = intent.getParcelableExtra<Song>(CURRENT_SONG)
//        var albumCover = currentSong.largeImageID
//        if (currentSong.title == "Thought Contagion") albumCover = R.drawable.muse_cover
//        ivAlbumCover.setBackgroundResource(albumCover)
//        tvArtist.text = currentSong.artist
//        tvSongName.text = currentSong.title
//
//        // General player components
//        tvPlayCount.text = "${this.playCount} plays"
//        btnPlay.setOnClickListener { this.incrementPlay() }
//        btnNext.setOnClickListener { this.announce("next") }
//        btnPrev.setOnClickListener { this.announce("previous") }
//    }
//
//    private fun incrementPlay() {
//        this.playCount++
//        tvPlayCount.text = "${this.playCount} plays"
//    }
//
//    private fun announce(direction : String) {
//        Toast.makeText(this, "Skipping to $direction track", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun createUsername(): String {
//        return "${adjectives.random()}${nouns.random()}"
//    }
//}