package com.example.michaeljacksonsong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.michaeljacksonsong.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding
    private lateinit var collection: Collection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent!=null && intent.extras!=null){
            collection=intent.getSerializableExtra("song") as Collection
        }

        Glide.with(applicationContext).load(collection.image).into(binding.imageview)

        binding.tvGenre.text=collection.primaryGenreName
        binding.tvArtist.text=collection.artistName
        binding.tvTrackName.text=collection.trackName
        binding.tvCollection.text=collection.collectionName
    }
}