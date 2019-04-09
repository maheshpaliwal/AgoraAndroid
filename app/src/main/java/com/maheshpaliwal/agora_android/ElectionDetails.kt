package com.maheshpaliwal.agora_android


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// Tabbed activity to show election details such as basic, voter, ballots, results

class ElectionDetails : Fragment() {

    override fun onActivityCreated(
        savedInstanceState: Bundle?) {
        // initializing variables
        var tabLayout: TabLayout? = view!!.findViewById<TabLayout>(R.id.tabLayout)
        var viewPager: ViewPager?=view!!.findViewById<ViewPager>(R.id.viewPager)
        // adding tabs in tablayout
        // basic tab
        tabLayout!!.addTab(tabLayout!!.newTab().setText("BASIC"))
        // voters tab
        tabLayout!!.addTab(tabLayout!!.newTab().setText("VOTERS"))
        // ballots tab
        tabLayout!!.addTab(tabLayout!!.newTab().setText("BALLOTS"))
        // results tab
        tabLayout!!.addTab(tabLayout!!.newTab().setText("RESULTS"))
        // defining tab layout gravity
        tabLayout!!.tabGravity = TabLayout.GRAVITY_CENTER

        //adapter for tabbed activity
        val adapter = ElectionDetailsAdapter(this,childFragmentManager, tabLayout.tabCount)
        viewPager!!.adapter = adapter
        // on Page Change Listener
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        // Tab selected listener
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_election_details, container, false)

    }


}
