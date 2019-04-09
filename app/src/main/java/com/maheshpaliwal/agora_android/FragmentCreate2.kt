package com.maheshpaliwal.agora_android


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.election_card.*
import kotlinx.android.synthetic.main.fragment_fragment_create2.*
import org.xml.sax.InputSource
import java.text.SimpleDateFormat
import java.util.*

// Step 2 of creating election
class FragmentCreate2 : Fragment() {
    // declare variables
    // start date
    var editText_date: TextView? = null
    // end date
    var end_date:TextView?=null
    // calendar
    var cal = Calendar.getInstance()
    // start time
    var start_time:String?=null
    // end time
    var end_time:String?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val button_next:Button=this.btn_next
        val button_prev:Button=this.btn_prev
        val election_name=arguments!!.getString("AGORA_ELECTION_TITLE")
        val election_desc=arguments!!.getString("AGORA_ELECTION_DESCRIPTION")
        // next
        button_next.setOnClickListener{
            val fragment = FragmentCreate3()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            args.putString("START_TIME",start_time)
            args.putString("END_TIME",end_time)
            args.putString("AGORA_ELECTION_TITLE",election_name)
            args.putString("AGORA_ELECTION_DESCRIPTION",election_desc)
            fragment.arguments= args
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        // previous
        button_prev.setOnClickListener{
            val fragment = FragmentCreate()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            args.putString("START_TIME",start_time)
            args.putString("END_TIME",end_time)
            args.putString("AGORA_ELECTION_TITLE",election_name)
            args.putString("AGORA_ELECTION_DESCRIPTION",election_desc)
            fragment.arguments= args
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }
        editText_date = this.input_start_date
        end_date=this.input_end_date
        // date listner starting date
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        // date listner end date
        val dateSetListener2= object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView2()
            }
        }
        editText_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val timeSetListner = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    editText_date!!.text =editText_date!!.text.toString()+","+ SimpleDateFormat("HH:mm").format(cal.time)
                    val sdf= SimpleDateFormat("HH:MM:SS")
                    sdf.timeZone= TimeZone.getTimeZone("UTC")

                    start_time+=sdf.format(cal.time)+"Z"


                }
                TimePickerDialog(context,timeSetListner, cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE),true).show()
                DatePickerDialog(context,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })


        end_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val timeSetListner = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    end_date!!.text =end_date!!.text.toString()+","+ SimpleDateFormat("HH:mm").format(cal.time)
                    val sdf= SimpleDateFormat("HH:MM:SS")
                    sdf.timeZone= TimeZone.getTimeZone("UTC")
                    end_time+=sdf.format(cal.time)+"Z"
                }
                TimePickerDialog(context,timeSetListner, cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE),true).show()
                DatePickerDialog(context,
                    dateSetListener2,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show() }

        })
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_create2, container, false)
    }
   private fun updateDateInView() {
       val myFormat = "yyyy-MM-dd" // mention the format you need
       val sdf = SimpleDateFormat(myFormat, Locale.US)
       val sdf2=SimpleDateFormat(myFormat)
       sdf2.timeZone= TimeZone.getTimeZone("UTC")
       editText_date!!.text= sdf.format(cal.getTime())
       start_time=sdf2.format(cal.getTime())+'T'
    }

    private fun updateDateInView2() {

        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf2=SimpleDateFormat(myFormat)
        sdf2.timeZone=TimeZone.getTimeZone("UTC")
        end_date!!.text=sdf.format(cal.getTime())
        end_time=sdf2.format(cal.getTime())
    }


}
