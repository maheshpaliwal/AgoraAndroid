package com.maheshpaliwal.agora_android.adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.maheshpaliwal.agora_android.fragments.mainActivityFragments.ElectionDetails
import com.maheshpaliwal.agora_android.fragments.viewElectionFragments.fragment_ballots
import com.maheshpaliwal.agora_android.fragments.viewElectionFragments.fragment_basic
import com.maheshpaliwal.agora_android.fragments.viewElectionFragments.fragment_results
import com.maheshpaliwal.agora_android.fragments.viewElectionFragments.fragment_voters

// adapter for tabbed activity election details
class ElectionDetailsAdapter(private val myContext: ElectionDetails, fm: FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                // Basic Fragment
                val fragment= fragment_basic()
                return fragment
            }
            1 -> {
                // Voters Fragment
                val fragment= fragment_voters()
                return fragment
            }
            2 -> {
                // Ballots Fragment
                val fragment= fragment_ballots()
                return fragment
            }
            3 ->{
                // results fragment
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