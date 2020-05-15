package com.benjweber.httpjsonparser

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.benjweber.httpjsonparser.model.AllSongs
import com.benjweber.httpjsonparser.model.Song
import com.google.gson.Gson

class ApiManager(private val context: Context) {
    private val q: RequestQueue = Volley.newRequestQueue(context)
    private val songsEndpoint = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json"

    fun fetchAllSongs(onSongsFetched: (List<Song>) -> Unit) {
        var allSongs: List<Song>? = null
        val req = StringRequest(
            Request.Method.GET, songsEndpoint,
            { res ->
                allSongs = Gson().fromJson(res, AllSongs::class.java).songs
                onSongsFetched(allSongs as List<Song>)
            }, {err ->
                Toast.makeText(context, "Sorry, there was an error retrieving you library.", Toast.LENGTH_LONG).show()
                Log.i("bjw", "Error. Response: ${err.networkResponse.statusCode}")
            })
        q.add(req)
    }
}