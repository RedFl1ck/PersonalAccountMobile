package com.example.coursework.ui.about

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.coursework.ui.about.screens.Screen1
import com.example.coursework.ui.about.screens.Screen2
import com.example.coursework.ui.about.screens.Screen3
import com.example.coursework.user.RecoveryPasswordEmail
import com.example.coursework.user.RecoveryPasswordPhone

@Suppress("DEPRECATION")
internal class AboutTabsAdapter(
    var context: Context,
    fm: FragmentManager,
    private var totalTabs: Int
) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Screen1()
            }
            1 -> {
                Screen2()
            }
            2 -> {
                Screen3()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}