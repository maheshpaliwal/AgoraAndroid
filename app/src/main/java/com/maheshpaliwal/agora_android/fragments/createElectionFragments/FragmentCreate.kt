package com.maheshpaliwal.agora_android.fragments.createElectionFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.maheshpaliwal.agora_android.R
import kotlinx.android.synthetic.main.fragment_fragment_create.*

// Fragment for creating elections
class FragmentCreate : Fragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // defining variables
        // next fragment
        val buttonNext: Button= this.btn_next
        // election title
        val electiontitle=this.input_election_name.toString()
        // election description
        val election_description=this.input_description.toString()
        buttonNext.setOnClickListener{
            val fragment = FragmentCreate2()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            // sending data to another fragment
            args.putString("AGORA_ELECTION_TITLE",electiontitle)
            args.putString("AGORA_ELECTION_DESCRIPTION",election_description)
            fragment.arguments= args
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
        return inflater.inflate(R.layout.fragment_fragment_create, container, false)
    }
}
