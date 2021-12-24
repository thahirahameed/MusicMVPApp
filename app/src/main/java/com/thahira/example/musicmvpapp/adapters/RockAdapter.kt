package com.thahira.example.musicmvpapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thahira.example.musicmvpapp.R
import com.thahira.example.musicmvpapp.model.Result

class RockAdapter(
    private val previewClick: PreviewClick,
    private val rockList: MutableList<Result> = mutableListOf()
): RecyclerView.Adapter<RockViewHolder>(){

    fun updateRock(newRock : List<Result>)
    {
        rockList.clear()
        rockList.addAll(newRock)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RockViewHolder {
        val rockView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.songlayout,parent,false)
        return RockViewHolder(rockView)
    }

    override fun onBindViewHolder(holder: RockViewHolder, position: Int) {
        val rock = rockList[position]
        holder.artistNam.text = rock.artistName
        holder.collectionNam.text = rock.collectionName
        holder.artworkUr60.text = rock.artworkUrl60
        holder.trackPrice.text = rock.collectionPrice.toString()

        holder.itemView.setOnClickListener{
            previewClick.previewSong(rock.previewUrl, rock.trackName)
        }

    }

    override fun getItemCount(): Int = rockList.size

}

class RockViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val artistNam: TextView = itemView.findViewById(R.id.artist_name)
    val collectionNam: TextView = itemView.findViewById(R.id.collection_name)
    val artworkUr60: TextView = itemView.findViewById(R.id.artwork_url)
    val trackPrice: TextView = itemView.findViewById(R.id.track_price)


}