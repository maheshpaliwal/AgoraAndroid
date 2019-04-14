package com.maheshpaliwal.agora_android.fragments.createElectionFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import com.maheshpaliwal.agora_android.R
import kotlinx.android.synthetic.main.fragment_fragment_create4.*

// Step 4 of creating elections
class FragmentCreate4 : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val buttonNext: Button =this.btn_next
        val buttonPrev:Button=this.btn_prev
        val startTime=arguments!!.getString("START_TIME")
        val endTime=arguments!!.getString("END_TIME")
        val electionName=arguments!!.getString("AGORA_ELECTION_TITLE")
        val electionDesc=arguments!!.getString("AGORA_ELECTION_DESCRIPTION")
        val candidate=arguments!!.getStringArray("AGORA_CANDIDATE")
        val mySpinner:Spinner=this.spinner1
        val algorithm=mySpinner.getSelectedItem().toString()
        buttonNext.setOnClickListener{
            val fragment = FragmentCreate5()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            args.putString("START_TIME",startTime)
            args.putString("END_TIME",endTime)
            args.putString("AGORA_ELECTION_TITLE",electionName)
            args.putString("AGORA_ELECTION_DESCRIPTION",electionDesc)
            args.putStringArray("AGORA_CANDIDATE",candidate)
            args.putString("AGORA_ALGORITHM",algorithm)
            fragment.arguments= args
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        buttonPrev.setOnClickListener{
            val fragment = FragmentCreate3()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            args.putString("START_TIME",startTime)
            args.putString("END_TIME",endTime)
            args.putString("AGORA_ELECTION_TITLE",electionName)
            args.putString("AGORA_ELECTION_DESCRIPTION",electionDesc)
            args.putStringArray("AGORA_CANDIDATE",candidate)
            args.putString("AGORA_ALGORITHM",algorithm)
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
        return inflater.inflate(R.layout.fragment_fragment_create4, container, false)
    }
}
