package com.maheshpaliwal.agora_android


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment_all.*
import kotlinx.android.synthetic.main.fragment_fragment_create3.*
import kotlinx.android.synthetic.main.toast_layout.*
import java.util.*

// Step 3 of creating elections
class FragmentCreate3 : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // declare variables
        val editText:EditText=this.input_candidate_name
        var textView:TextView=this.input_cand
        val button_next:Button=this.btn_next
        val button_prev:Button=this.btn_prev
        val start_time=arguments!!.getString("START_TIME")
        val end_time=arguments!!.getString("END_TIME")
        val election_name=arguments!!.getString("AGORA_ELECTION_TITLE")
        val election_desc=arguments!!.getString("AGORA_ELECTION_DESCRIPTION")
        var candidate:Array<String>?=null
        //next
        button_next.setOnClickListener{
            val fragment = FragmentCreate4()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            //putting data
            args.putString("START_TIME",start_time)
            args.putString("END_TIME",end_time)
            args.putString("AGORA_ELECTION_TITLE",election_name)
            args.putString("AGORA_ELECTION_DESCRIPTION",election_desc)
            args.putStringArray("AGORA_CANDIDATE",candidate)
            fragment.arguments= args
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        //previous
        button_prev.setOnClickListener{
            val fragment = FragmentCreate2()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            //putting data
            args.putString("START_TIME",start_time)
            args.putString("END_TIME",end_time)
            args.putString("AGORA_ELECTION_TITLE",election_name)
            args.putString("AGORA_ELECTION_DESCRIPTION",election_desc)
            args.putStringArray("AGORA_CANDIDATE",candidate)
            fragment.arguments= args
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        textView.visibility=View.GONE
        val button:AppCompatButton=this.btn_add
        var i:Int=0
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                textView.visibility=View.VISIBLE
                candidate?.set(i, editText.text.toString())
                i++
                textView.text=textView.text.toString()+editText.text+System.getProperty("line.separator")
            }
        })
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_create3, container, false)
    }
}
