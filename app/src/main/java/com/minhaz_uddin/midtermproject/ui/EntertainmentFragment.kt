package com.minhaz_uddin.midtermproject.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.minhaz_uddin.midtermproject.R
import com.minhaz_uddin.midtermproject.adapter.NewsAdapter
import com.minhaz_uddin.midtermproject.model.CustomArticles
import com.minhaz_uddin.midtermproject.viewModel.NewsViewModel
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex.Empty


class EntertainmentFragment : Fragment() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var newslist:List<CustomArticles>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh_ent)
        recyclerView=view.findViewById(R.id.ent_recycler)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        val viewModel= ViewModelProvider(this)[NewsViewModel::class.java]
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.deleteAll()
            viewModel.getAllNews()
            swipeRefreshLayout.isRefreshing=false
            recyclerView.adapter?.notifyDataSetChanged()

        }
        viewModel.entertainmentNews.observe(viewLifecycleOwner){
            recyclerView.adapter= NewsAdapter(requireContext(),viewModel,it)
            newslist=it
        }


    }


}