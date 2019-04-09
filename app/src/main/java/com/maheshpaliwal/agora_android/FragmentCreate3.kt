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
        val buttonNext:Button=this.btn_next
        val buttonPrev:Button=this.btn_prev
        val startTime=arguments!!.getString("START_TIME")
        val endTime=arguments!!.getString("END_TIME")
        val electionName=arguments!!.getString("AGORA_ELECTION_TITLE")
        val electionDesc=arguments!!.getString("AGORA_ELECTION_DESCRIPTION")
        var candidate:Array<String>?=null
        //next
        buttonNext.setOnClickListener{
            val fragment = FragmentCreate4()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            //putting data
            args.putString("START_TIME",startTime)
            args.putString("END_TIME",endTime)
            args.putString("AGORA_ELECTION_TITLE",electionName)
            args.putString("AGORA_ELECTION_DESCRIPTION",electionDesc)
            args.putStringArray("AGORA_CANDIDATE",candidate)
            fragment.arguments= args
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        //previous
        buttonPrev.setOnClickListener{
            val fragment = FragmentCreate2()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            //putting data
            args.putString("START_TIME",startTime)
            args.putString("END_TIME",endTime)
            args.putString("AGORA_ELECTION_TITLE",electionName)
            args.putString("AGORA_ELECTION_DESCRIPTION",electionDesc)
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
