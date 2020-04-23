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
        val oldId = oldLibrary[oldItemPosition].id
        val newId = newLibrary[newItemPosition].id
        return oldId == newId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldSong = oldLibrary[oldItemPosition]
        val newSong = newLibrary[newItemPosition]
        return oldSong.title == newSong.title &&
                oldSong.artist == newSong.artist &&
                oldSong.smallImageID == newSong.smallImageID
    }

}