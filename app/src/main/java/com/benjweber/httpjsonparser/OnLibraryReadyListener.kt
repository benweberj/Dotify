package com.benjweber.httpjsonparser

import com.benjweber.httpjsonparser.model.Song

interface OnLibraryReadyListener { fun onLibraryReady(library: List<Song>) }