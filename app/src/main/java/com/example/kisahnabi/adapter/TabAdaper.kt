package com.example.kisahnabi.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kisahnabi.fragment.NabiFragment
import com.example.kisahnabi.fragment.RasulFragment

class TabAdaper (FragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(FragmentManager, lifecycle){
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                NabiFragment()
            }
            1 -> {
                RasulFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}