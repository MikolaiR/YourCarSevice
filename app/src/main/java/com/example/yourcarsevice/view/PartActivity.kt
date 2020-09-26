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
import kotlinx.android.synthetic.main.content_main.*

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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                NavHostFragment.findNavController(login_nav_host_fragment).navigate(R.id.action_PartListFragment_to_settingsFragment)
                true
            }
            R.id.synchronization -> {
                partItemFragmentViewModel.getPartListResponse()
                true
            }
            R.id.exit_to_app -> {
                sharedPrefs.edit().remove(BEARER_TOKEN).apply()
                startActivity(Intent(this,MainActivity::class.java))
                //NavHostFragment.findNavController(nav_host_fragment).navigate(R.id.action_partFragment_to_loginFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


/*
findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
}*/
