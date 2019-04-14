package com.maheshpaliwal.agora_android.fragments.votingProcessFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maheshpaliwal.agora_android.R


/* Preferential Ballot */
class Cast_vote : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for preferential ballot
        return inflater.inflate(R.layout.fragment_cast_vote, container, false)
    }
}
