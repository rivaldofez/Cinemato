package com.rivaldofez.cinemato.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(){
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
                    navController.navigate(R.id.favoriteFragment)
                }
                2 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.tvShowFragment)
                }

            }
        }

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            if (destination.id == R.id.movieFragment || destination.id == R.id.tvShowFragment || destination.id == R.id.favoriteFragment) {
                binding.bnavMain.visibility = View.VISIBLE
                binding.toolbarMain.visibility = View.VISIBLE
            } else {
                binding.bnavMain.visibility = View.GONE
                binding.toolbarMain.visibility = View.GONE
            }
        }
    }
}