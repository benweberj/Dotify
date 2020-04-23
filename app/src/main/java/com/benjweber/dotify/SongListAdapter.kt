package com.benjweber.dotify

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

class SongListAdapter(library: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    private var library: List<Song> = library.toList()
    var onSongClicked: ((song: Song) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount() = library.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = library[position]
        holder.attach(song)
    }

    fun shuffleLibrary(newLibrary: List<Song>) {
        val callback = SongDiffCallback(library, newLibrary)
        val diff = DiffUtil.calculateDiff(callback)
        diff.dispatchUpdatesTo(this)
        library = newLibrary
    }

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSongName by lazy { itemView.findViewById<TextView>(R.id.tvSongName) }
        private val tvArtistName by lazy { itemView.findViewById<TextView>(R.id.tvArtistName) }
        private val imgThumbnail by lazy { itemView.findViewById<ImageView>(R.id.imgThumbnail) }

        fun attach(song: Song) {
            tvSongName.text = song.title
            tvArtistName.text = song.artist
            var album = song.smallImageID
            if (song.title == "Thought Contagion") album = R.drawable.muse_cover
            imgThumbnail.setImageResource(album)

            itemView.setOnClickListener {
                onSongClicked?.invoke(song)
            }
        }
    }
}