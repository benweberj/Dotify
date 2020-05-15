package com.benjweber.httpjsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.benjweber.httpjsonparser.fragment.LibraryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("bjw", "MainActivity starting...")

        val app = application as MusicApp
//        app.songClickedListener = this or something like that
        val libFrag = LibraryFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, libFrag, "libfrag")
                .commit()

        ibShuffle.setOnClickListener {
            
        }

        supportFragmentManager.addOnBackStackChangedListener {
            setNav()
        }
    }

    private fun setNav() {
        val hasBS = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(hasBS)
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onSupportNavigateUp()
    }
}