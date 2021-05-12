package com.example.michaeljacksonsong

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.michaeljacksonsong.databinding.ActivityMainBinding
import org.json.JSONException

class MainActivity : AppCompatActivity(), CollectionAdapter.IOnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var collectionAdapter: CollectionAdapter
    private var requestQueue: RequestQueue? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup the recyclerview
        setRecyclerView()

        //get songs from Itunes API using volley
        getSongFromAPI()

    }

    private fun getSongFromAPI() {

        binding.progressBar.visibility = View.VISIBLE

        val url = "https://itunes.apple.com/search?term=Michael+jackson"

        requestQueue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val collectionList = mutableListOf<Collection>()
                val jsonArray = response.getJSONArray("results")
                for (i in 0 until jsonArray.length()) {
                    val collectionJson = jsonArray.getJSONObject(i)

                    if (collectionJson.has("trackName") &&
                        collectionJson.has("artistName") &&
                        collectionJson.has("primaryGenreName") &&
                        collectionJson.has("collectionName") &&
                        collectionJson.has("artworkUrl60")
                    ) {

                        val song = Collection(
                            collectionJson.getString("trackName"),
                            collectionJson.getString("primaryGenreName"),
                            collectionJson.getString("artistName"),
                            collectionJson.getString("collectionName"),
                            collectionJson.getString("artworkUrl60")
                        )
                        collectionList.add(song)
                    }
                }

                collectionAdapter.submitList(collectionList)
                binding.progressBar.visibility = View.GONE

            } catch (e: JSONException) {
                e.printStackTrace()
                binding.progressBar.visibility = View.GONE

            }

        }, { error ->
            error.printStackTrace()
            binding.progressBar.visibility = View.GONE
        })
        requestQueue?.add(request)

    }

    private fun setRecyclerView() {
        collectionAdapter = CollectionAdapter(this)
        binding.recyclerView.adapter = collectionAdapter
    }

    override fun onItemClick(item: Collection) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("song", item)
        startActivity(intent)

    }
}