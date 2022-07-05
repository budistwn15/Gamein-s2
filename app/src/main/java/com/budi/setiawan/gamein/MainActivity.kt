package com.budi.setiawan.gamein

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.budi.setiawan.gamein.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBar.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var title = getString(R.string.app_name)
        when (item.itemId) {
            R.id.nav_home -> {
                findNavController(R.id.nav_host_fragment_container).navigate(R.id.homeFragment)
                title = getString(R.string.app_name)
            }
            R.id.nav_favorite -> {
                findNavController(R.id.nav_host_fragment_container).navigate(R.id.favoriteFragment)
                title = getString(R.string.favorite)
            }
            R.id.nav_setting -> {
                findNavController(R.id.nav_host_fragment_container).navigate(R.id.settingFragment)
                title = getString(R.string.setting)
            }
        }
        supportActionBar?.title = title

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q &&
            isTaskRoot &&
            (supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount
                ?: 0) == 0 &&
            supportFragmentManager.backStackEntryCount == 0
        ) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}