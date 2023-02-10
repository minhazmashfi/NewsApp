package com.minhaz_uddin.midtermproject.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minhaz_uddin.midtermproject.R
import com.minhaz_uddin.midtermproject.adapter.BookmarkAdapter
import com.minhaz_uddin.midtermproject.adapter.NewsAdapter
import com.minhaz_uddin.midtermproject.model.BookmarkArticle
import com.minhaz_uddin.midtermproject.model.CustomArticles
import com.minhaz_uddin.midtermproject.viewModel.NewsViewModel


class BookmarkFragment : Fragment() {
    private lateinit var newslist:List<BookmarkArticle>
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel=ViewModelProvider(this)[NewsViewModel::class.java]
        recyclerView=view.findViewById(R.id.recycler_book)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        viewModel.getReadAllBookmarks().observe(viewLifecycleOwner) {
            recyclerView.adapter = BookmarkAdapter(requireContext(),viewModel, it)
            newslist=it
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
                        val searchList = mutableListOf<BookmarkArticle>()
                        viewModel.getReadAllBookmarks().value?.map {
                            if (it.title?.contains(msg, ignoreCase = true) == true) {
                                searchList.add(it)
                                Log.d("book", "onQueryTextChange:${searchList.size} ")
                            }

                        }
                        if(searchList.isEmpty()){
                            Toast.makeText(requireContext(),"No results found", Toast.LENGTH_SHORT).show()
                        }
                        recyclerView.adapter =
                            BookmarkAdapter(requireContext(), viewModel, searchList)

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