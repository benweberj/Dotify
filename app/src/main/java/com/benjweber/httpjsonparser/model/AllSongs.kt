package com.benjweber.httpjsonparser.model

data class AllSongs(
  val title: String,
  val numOfSongs: Int,
  val songs: List<Song>
)