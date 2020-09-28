package com.example.yourcarsevice.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.yourcarsevice.R
import com.example.yourcarsevice.view.authorization.BEARER_TOKEN
import com.example.yourcarsevice.view.authorization.PREFS_NAME
import com.example.yourcarsevice.viewmodel.PartListFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.content_part.*

class PartActivity : AppCompatActivity() {

    private val partItemFragmentViewModel by viewModels<PartListFragmentViewModel>()

    private val sharedPrefs by lazy {
        this.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<BottomNavigationView>(R.id.partBottomNavigation).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_bottom_navigation_part_list -> {
                    NavHostFragment.findNavController(part_nav_host_fragment).navigate(R.id.action_StatisticFragment_to_PartListFragment)
                    true
                }
                R.id.item_bottom_navigation_statistic -> {
                    NavHostFragment.findNavController(part_nav_host_fragment).navigate(R.id.action_PartListFragment_to_StatisticFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_settings -> {
                startActivity(Intent(this,SettingsActivity::class.java))
                true
            }
            R.id.item_synchronization -> {
                partItemFragmentViewModel.getPartListResponse()
                true
            }
            R.id.item_log_out -> {
                sharedPrefs.edit().remove(BEARER_TOKEN).apply()
                startActivity(Intent(this,MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

