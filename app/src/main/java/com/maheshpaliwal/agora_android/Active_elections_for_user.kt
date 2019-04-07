package com.maheshpaliwal.agora_android


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_active_elections_for_user.*
import kotlinx.android.synthetic.main.fragment_fragment_create.*
import org.w3c.dom.Text


// Fragment for showing active elections for user

class Active_elections_for_user : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val button_cast: TextView= this.cast_vote
        val button_range:TextView=this.cast_vote2
        button_cast.setOnClickListener{
             /* Calling cast vote */
            val fragment = Cast_vote()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


        }
        button_range.setOnClickListener{
            val fragment = range_seekbar()
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
