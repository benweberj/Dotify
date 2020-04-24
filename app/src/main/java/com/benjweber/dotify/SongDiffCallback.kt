package com.benjweber.dotify

import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongDiffCallback(private val oldLibrary: List<Song>, private val newLibrary: List<Song>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldLibrary.size
    }

    override fun getNewListSize(): Int {
        return newLibrary.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLibrary[oldItemPosition].id == newLibrary[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldSong = oldLibrary[newItemPosition]
        val newSong = newLibrary[oldItemPosition]
        return oldSong == newSong
    }
}