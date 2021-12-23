package com.thahira.example.musicmvpapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thahira.example.musicmvpapp.views.ClassicFragment
import com.thahira.example.musicmvpapp.views.PopFragment
import com.thahira.example.musicmvpapp.views.RockFragment

class FragmentsAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle)
{
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> ClassicFragment.newInstance()
            1-> RockFragment.newInstance()
            2-> PopFragment.newInstance()
            else -> ClassicFragment.newInstance()
    }
}

}