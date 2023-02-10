package com.minhaz_uddin.midtermproject.ui

import android.os.Bundle
import android.util.Log
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
import androidx.room.util.query
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.minhaz_uddin.midtermproject.R
import com.minhaz_uddin.midtermproject.adapter.NewsAdapter
import com.minhaz_uddin.midtermproject.model.CustomArticles
import com.minhaz_uddin.midtermproject.viewModel.NewsViewModel
import java.util.Locale.filter


class BusinessFragment : Fragment() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchList:List<CustomArticles>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel=ViewModelProvider(this)[NewsViewModel::class.java]
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh)
        recyclerView=view.findViewById(R.id.business_recycler)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        viewModel.readbusinessNews.observe(viewLifecycleOwner){
            Log.d("category", "onViewCreated:started ")
            recyclerView.adapter=NewsAdapter(requireContext(),viewModel,it)
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.deleteAll()
            viewModel.getAllNews()
            swipeRefreshLayout.isRefreshing=false
            recyclerView.adapter?.notifyDataSetChanged()

        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView: SearchView = searchItem.actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                    android.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return false
                        }

                    override fun onQueryTextChange(msg: String): Boolean {
                        val searchList = mutableListOf<CustomArticles>()
                        viewModel.readbusinessNews.value?.map {
                            if (it.title?.contains(msg, ignoreCase = true) == true) {
                                searchList.add(it)


                            }

                        }

                        recyclerView.adapter =
                            NewsAdapter(requireContext(), viewModel, searchList)
                        return false
                    }

                })
            }


            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

}