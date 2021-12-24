package com.thahira.example.musicmvpapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thahira.example.musicmvpapp.R
import com.thahira.example.musicmvpapp.model.Result

class PopAdapter(
    private val previewClick: PreviewClick,
    private val popList: MutableList<Result> = mutableListOf()
): RecyclerView.Adapter<PopViewHolder>(){

    fun updatePop(newPop : List<Result>)
    {
        popList.clear()
        popList.addAll(newPop)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopViewHolder {
        val popView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.songlayout,parent,false)
        return PopViewHolder(popView)
    }

    override fun onBindViewHolder(holder: PopViewHolder, position: Int) {
        val pop = popList[position]
        holder.artistNam.text = pop.artistName
        holder.collectionNam.text = pop.collectionName
        holder.artworkUr60.text = pop.artworkUrl60
        holder.trackPrice.text = pop.collectionPrice.toString()

        holder.itemView.setOnClickListener{
            previewClick.previewSong(pop.previewUrl,pop.trackName)
        }

    }

    override fun getItemCount(): Int = popList.size

}

class PopViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val artistNam: TextView = itemView.findViewById(R.id.artist_name)
    val collectionNam: TextView = itemView.findViewById(R.id.collection_name)
    val artworkUr60: TextView = itemView.findViewById(R.id.artwork_url)
    val trackPrice: TextView = itemView.findViewById(R.id.track_price)

}