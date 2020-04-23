package com.benjweber.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var playCount : Int = Random.nextInt(100, 10000)
    private val adjectives = listOf("Bent", "Weird", "Toasty", "Irregular", "Quantum", "Springloaded", "Spicy")
    private val nouns = listOf("Rectangle", "Mama", "Program", "Bean", "Meme", "Human")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPlayCount.text = "${this.playCount} plays"
        btnPlay.setOnClickListener { this.incrementPlay() }
        btnNext.setOnClickListener { this.announce("next") }
        btnPrev.setOnClickListener { this.announce("previous") }
        btnChangeUser.setOnClickListener { this.changeUser() }
        tvUsername.setOnLongClickListener {
            this.changeUser()
            return@setOnLongClickListener true // not sure why this is needed for a long click
        }

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
            etUsername.apply {
                visibility = View.VISIBLE
                requestFocus()
            }
            tvUsername.visibility = View.INVISIBLE
            btnChangeUser.text = "Apply"
        } else {
            if (etUsername.text.isEmpty()) {
                val suggested = createUsername()
                etUsername.setText(suggested)
                Toast.makeText(this, "Username can't be empty. '$suggested' is available!", Toast.LENGTH_LONG).show()
            } else {
                tvUsername.apply {
                    text = etUsername.text.trim()
                    visibility = View.VISIBLE
                }
                etUsername.apply {
                    setText("")
                    visibility = View.INVISIBLE
                }
                btnChangeUser.text = "Change User"
            }
        }
    }

    private fun createUsername(): String {
        return "${adjectives.random()}${nouns.random()}"
    }
}