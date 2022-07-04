package com.rivaldofez.cinemato.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)

        binding.bnavMain.setNavigationChangeListener{_, position ->
            when(position) {
                0 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.movieFragment)
                }
                1 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.tvShowFragment)
                }
                2 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.feedFragment)
                }
                3 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.favoriteFragment)
                }
            }
        }
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}