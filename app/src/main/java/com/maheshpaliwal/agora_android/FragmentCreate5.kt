package com.maheshpaliwal.agora_android


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_fragment_create5.*

// last step of creating election
class FragmentCreate5 : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val button_next: Button =this.btn_next
        val button_prev:Button=this.btn_prev
        val start_time=arguments!!.getString("START_TIME")
        val end_time=arguments!!.getString("END_TIME")
        val election_name=arguments!!.getString("AGORA_ELECTION_TITLE")
        val election_desc=arguments!!.getString("AGORA_ELECTION_DESCRIPTION")
        val candidate=arguments!!.getStringArray("AGORA_CANDIDATE")
        val algorithm=arguments!!.getString("AGORA_ALGORITHM")
        //finish election
        button_next.setOnClickListener{
        }
        button_prev.setOnClickListener{
            val fragment = FragmentCreate4()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            args.putString("START_TIME",start_time)
            args.putString("END_TIME",end_time)
            args.putString("AGORA_ELECTION_TITLE",election_name)
            args.putString("AGORA_ELECTION_DESCRIPTION",election_desc)
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
