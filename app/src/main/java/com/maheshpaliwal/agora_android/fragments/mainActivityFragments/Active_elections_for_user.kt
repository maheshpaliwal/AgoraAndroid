package com.maheshpaliwal.agora_android.fragments.mainActivityFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maheshpaliwal.agora_android.R
import com.maheshpaliwal.agora_android.fragments.votingProcessFragments.Cast_vote
import com.maheshpaliwal.agora_android.fragments.votingProcessFragments.range_seekbar
import com.maheshpaliwal.agora_android.fragments.votingProcessFragments.score_voting
import kotlinx.android.synthetic.main.fragment_active_elections_for_user.*


// Fragment for showing active elections for user

class Active_elections_for_user : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //initialize required variables
        val buttonCast: TextView= this.cast_vote// preferential ballot redirect button
        val buttonRange:TextView=this.cast_vote2 // range ballot redirect button
        val buttonScore:TextView=this.cast_vote3 // score ballot redirect button
        // seting onClickListener
        buttonCast.setOnClickListener{
             /* Calling preferential ballot */
            val fragment = Cast_vote()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
            // calling range ballot
        buttonRange.setOnClickListener{
            val fragment = range_seekbar()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        // calling score ballot
        buttonScore.setOnClickListener{
            val fragment = score_voting()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_active_elections_for_user, container, false)
    }
}
