package com.thahira.example.musicmvpapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thahira.example.musicmvpapp.R
import com.thahira.example.musicmvpapp.model.Result

class ClassicAdapter(
    private val previewClick: PreviewClick,
    private val classicList: MutableList<Result> = mutableListOf()
): RecyclerView.Adapter<ClassicViewHolder>(){

    fun updateClassic(newClassic : List<Result>)
    {
        classicList.clear()
        classicList.addAll(newClassic)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassicViewHolder {
            val classicView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.songlayout,parent,false)
            return ClassicViewHolder(classicView)
    }

    override fun onBindViewHolder(holder: ClassicViewHolder, position: Int) {
        val classic = classicList[position]
        holder.artistNam.text = classic.artistName
        holder.collectionNam.text = classic.collectionName
        holder.artworkUr60.text = classic.artworkUrl60
        holder.trackPrice.text = classic.collectionPrice.toString()

        holder.itemView.setOnClickListener{
            previewClick.previewSong(classic.previewUrl,classic.trackName)
        }
    }

    override fun getItemCount(): Int = classicList.size

}

class ClassicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val artistNam: TextView = itemView.findViewById(R.id.artist_name)
    val collectionNam: TextView = itemView.findViewById(R.id.collection_name)
    val artworkUr60: TextView = itemView.findViewById(R.id.artwork_url)
    val trackPrice: TextView = itemView.findViewById(R.id.track_price)

}
