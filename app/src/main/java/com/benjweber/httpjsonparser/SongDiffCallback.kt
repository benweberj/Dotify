package com.benjweber.httpjsonparser

import androidx.recyclerview.widget.DiffUtil
import com.benjweber.httpjsonparser.model.Song

class SongDiffCallback(private val oldLibrary: List<Song>, private val newLibrary: List<Song>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldLibrary.size
    }

    override fun getNewListSize(): Int {
        return newLibrary.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLibrary[oldItemPosition].title == newLibrary[newItemPosition].title
        // either make id or stick with this bad check
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldSong = oldLibrary[newItemPosition]
        val newSong = newLibrary[oldItemPosition]
        return oldSong == newSong
    }
}