package com.maheshpaliwal.agora_android.fragments.createElectionFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.maheshpaliwal.agora_android.R
import kotlinx.android.synthetic.main.fragment_fragment_create5.*

// last step of creating election
class FragmentCreate5 : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val buttonNext: Button =this.btn_next
        val buttonPrev:Button=this.btn_prev
        val startTime=arguments!!.getString("START_TIME")
        val endTime=arguments!!.getString("END_TIME")
        val electionName=arguments!!.getString("AGORA_ELECTION_TITLE")
        val electionDesc=arguments!!.getString("AGORA_ELECTION_DESCRIPTION")
        val candidate=arguments!!.getStringArray("AGORA_CANDIDATE")
        val algorithm=arguments!!.getString("AGORA_ALGORITHM")
        //finish election
        buttonNext.setOnClickListener{
        }
        buttonPrev.setOnClickListener{
            val fragment = FragmentCreate4()
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
        return inflater.inflate(R.layout.fragment_fragment_create5, container, false)
    }
}
