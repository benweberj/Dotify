package com.benjweber.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_song.*
import kotlinx.android.synthetic.main.activity_song_list.*

class AddSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)


        btnAddSong.setOnClickListener {
            Toast.makeText(this, "you clicked the shit", Toast.LENGTH_SHORT).show()
        }
    }
}
