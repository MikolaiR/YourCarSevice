package com.example.yourcarsevice.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.yourcarsevice.R
import com.example.yourcarsevice.viewmodel.PartItemFragmentViewModel
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(){

    private val partItemFragmentViewModel by viewModels<PartItemFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onStart() {
        super.onStart()
        partItemFragmentViewModel.theme.observe(this, Observer {
            when(it){
                this.getString(R.string.light_theme_values) -> AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO)
                this.getString(R.string.dark_theme_values) -> AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
               NavHostFragment.findNavController(nav_host_fragment).navigate(R.id.action_partFragment_to_settingsFragment)
                true
            }
            R.id.synchronization -> {
                partItemFragmentViewModel.getParts().observe(this, Observer {
                    partItemFragmentViewModel.synchronization(it)
                })
                true
            }
            R.id.exit_to_app -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}