package com.minhaz_uddin.midtermproject

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.minhaz_uddin.midtermproject.adapter.NewsAdapter
import com.minhaz_uddin.midtermproject.viewModel.NewsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController=navHostFragment.navController
        setupActionBarWithNavController(navController)
        val bottomNav=findViewById<BottomNavigationView>(R.id.newsFragment)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
               R.id.bookmark -> {findNavController(R.id.nav_host_fragment).navigate(R.id.bookmarkFragment)
                                      }
               R.id.home -> {
                   findNavController(R.id.nav_host_fragment).navigate(R.id.newsFragment)
                               }
            }
            true
    }
        }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()||super.onSupportNavigateUp()
    }

}