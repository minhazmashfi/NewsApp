package com.minhaz_uddin.midtermproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.minhaz_uddin.midtermproject.ui.*

class ViewPageAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 ->AllNewsFragment()
            1 -> HealthFragment()
            2 ->ScienceFragment()
            3 ->SportsFragment()
            4 ->EntertainmentFragment()
            5 ->BusinessFragment()
            else ->Fragment()
        }

    }


}