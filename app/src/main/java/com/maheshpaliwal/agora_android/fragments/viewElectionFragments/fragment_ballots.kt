package com.maheshpaliwal.agora_android.fragments.viewElectionFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maheshpaliwal.agora_android.R

// Showing Ballots Details

class fragment_ballots : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_ballots, container, false)
    }


}
