package com.benjweber.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var playCount : Int = kotlin.random.Random.nextInt(100, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPlayCount.text = "${this.playCount} plays"
        btnPlay.setOnClickListener { this.incrementPlay() }
        btnNext.setOnClickListener { this.announce("next") }
        btnPrev.setOnClickListener { this.announce("prev") }
        btnChangeUser.setOnClickListener { this.changeUser() }
    }

    private fun incrementPlay() {
        this.playCount++
        tvPlayCount.text = "${this.playCount} plays"
    }

    private fun announce(direction : String) {
        Toast.makeText(this, "Skipping to $direction track", Toast.LENGTH_SHORT).show()
    }

    private fun changeUser() {
        val action = btnChangeUser.text
        if (action == "Change User") {
            tvUsername.visibility = View.INVISIBLE
            etUsername.visibility = View.VISIBLE
            btnChangeUser.text = "Apply"
        } else {
            tvUsername.text = etUsername.text.trim()
            etUsername.setText("")
            tvUsername.visibility = View.VISIBLE
            etUsername.visibility = View.INVISIBLE
            btnChangeUser.text = "Change User"
        }
    }
}
