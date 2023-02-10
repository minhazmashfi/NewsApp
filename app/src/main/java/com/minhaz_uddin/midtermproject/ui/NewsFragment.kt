package com.minhaz_uddin.midtermproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.minhaz_uddin.midtermproject.R
import com.minhaz_uddin.midtermproject.adapter.NewsAdapter
import com.minhaz_uddin.midtermproject.adapter.ViewPageAdapter
import com.minhaz_uddin.midtermproject.viewModel.NewsViewModel

private lateinit var recyclerView: RecyclerView
private lateinit var viewModel: NewsViewModel
class NewsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)


        viewPager.adapter = ViewPageAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {

                0 -> tab.text = "All News"
                1 -> tab.text="Health News"
                2 -> tab.text = "Science"
                3 -> tab.text = "Sports"
                4 -> tab.text = "Entertainment"
                5 -> tab.text = "Business"
            }
        }.attach()
    }


}