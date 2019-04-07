package com.maheshpaliwal.agora_android


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_fragment_create.*


class FragmentCreate : Fragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {


        val button_next: Button= this.btn_next
        val election_title=this.input_election_name.toString()
        val election_description=this.input_description.toString()
        button_next.setOnClickListener{

            val fragment = FragmentCreate2()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            args.putString("AGORA_ELECTION_TITLE",election_title)
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
