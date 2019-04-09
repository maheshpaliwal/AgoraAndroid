package com.maheshpaliwal.agora_android


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// Elections Fragment with tabs all,pending, active, finished
class FragmentElection : Fragment() {
    override fun onActivityCreated(
        savedInstanceState: Bundle?) {
        // declare variables
        val token= arguments!!.getString("TOKEN_AGORA")
        // add tabs
        var tabLayout: TabLayout? = view!!.findViewById<TabLayout>(R.id.tabLayout)
        var viewPager: ViewPager?=view!!.findViewById<ViewPager>(R.id.viewPager)
        tabLayout!!.addTab(tabLayout!!.newTab().setText("ALL"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("PENDING"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("ACTIVE"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("FINISHED"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_CENTER
        val adapter = MyAdapter(activity,childFragmentManager, tabLayout.tabCount,token)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
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
        return inflater.inflate(R.layout.fragment_fragment_election, container, false)
    }
}
