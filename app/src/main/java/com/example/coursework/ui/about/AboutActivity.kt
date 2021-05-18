package com.example.coursework.ui.about

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework.R
import com.example.coursework.databinding.ActivityAboutBinding
import com.google.android.material.tabs.TabLayout

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tabs.newTab().let {  binding.tabs.addTab(it.setText(resources.getString(R.string.tab_text_1))) }
        binding.tabs.newTab().let {  binding.tabs.addTab(it.setText(resources.getString(R.string.tab_text_2))) }
        binding.tabs.newTab().let {  binding.tabs.addTab(it.setText(resources.getString(R.string.tab_text_3))) }
        binding.tabs.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = binding.tabs.tabCount.let {
            AboutTabsAdapter(applicationContext, supportFragmentManager, it)
        }
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabs))
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        supportActionBar?.elevation = 0F
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}