package com.maheshpaliwal.agora_android.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.maheshpaliwal.agora_android.fragments.electionFragments.fragment_active
import com.maheshpaliwal.agora_android.fragments.electionFragments.fragment_all
import com.maheshpaliwal.agora_android.fragments.electionFragments.fragment_finished
import com.maheshpaliwal.agora_android.fragments.electionFragments.fragment_pending

// Adapter for elections tabs

class MyAdapter(private val myContext: FragmentActivity?, fm: FragmentManager, private var totalTabs: Int,
                val token:String) : FragmentPagerAdapter(fm) {
    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                val fragment= fragment_all()
                val args = Bundle()
                args.putString("TOKEN_AGORA",token)
                fragment.arguments= args
                return fragment
            }
            1 -> {
                val fragment= fragment_pending()
                val args = Bundle()
                args.putString("TOKEN_AGORA",token)
                fragment.arguments= args
                return fragment
            }
            2 -> {
                val fragment= fragment_active()
                val args = Bundle()
                args.putString("TOKEN_AGORA",token)
                fragment.arguments= args
                return fragment
            }
            3 ->{
                val fragment= fragment_finished()
                val args = Bundle()
                args.putString("TOKEN_AGORA",token)
                fragment.arguments= args
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