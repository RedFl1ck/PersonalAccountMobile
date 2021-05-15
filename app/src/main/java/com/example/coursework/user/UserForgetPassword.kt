package com.example.coursework.user

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.coursework.R
import com.google.android.material.tabs.TabLayout

class UserForgetPassword : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_forget_password)
        supportActionBar?.hide()

        tabLayout = findViewById(R.id.recovery_tab_layout)
        viewPager = findViewById(R.id.recovery_view_pager)
        tabLayout?.newTab()?.let { tabLayout?.addTab(it.setText(resources.getString(R.string.phone_num))) }
        tabLayout?.newTab()?.let { tabLayout?.addTab(it.setText(resources.getString(R.string.e_mail))) }
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = tabLayout?.tabCount?.let {
            RecoveryTabsAdapter(this, supportFragmentManager,
                it
            )
        }
        viewPager?.adapter = adapter
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    fun close(view: View) {
        finish()
    }
}