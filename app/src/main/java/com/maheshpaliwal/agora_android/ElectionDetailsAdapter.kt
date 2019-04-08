package com.maheshpaliwal.agora_android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ElectionDetailsAdapter(private val myContext: ElectionDetails, fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                val fragment= fragment_basic()





                return fragment
            }
            1 -> {

                val fragment= fragment_voters()

                return fragment
            }
            2 -> {
                val fragment= fragment_ballots()


                return fragment
            }
            3 ->{
                val fragment= fragment_results()


                return fragment
            }
            else -> return null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}