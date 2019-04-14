package com.maheshpaliwal.agora_android.fragments.createElectionFragments


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.maheshpaliwal.agora_android.R
import kotlinx.android.synthetic.main.fragment_fragment_create2.*
import java.text.SimpleDateFormat
import java.util.*

// Step 2 of creating election
class FragmentCreate2 : Fragment() {
    // declare variables
    // start date
    var startDate: TextView? = null
    // end date
    var endDate:TextView?=null
    // calendar
    var cal = Calendar.getInstance()
    // start time
    var startTime:String?=null
    // end time
    var endtime:String?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val buttonNext:Button=this.btn_next
        val buttonPrev:Button=this.btn_prev
        val electionName=arguments!!.getString("AGORA_ELECTION_TITLE")
        val electionDesc=arguments!!.getString("AGORA_ELECTION_DESCRIPTION")
        // next
        buttonNext.setOnClickListener{
            val fragment = FragmentCreate3()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            args.putString("START_TIME",startTime)
            args.putString("END_TIME",endtime)
            args.putString("AGORA_ELECTION_TITLE",electionName)
            args.putString("AGORA_ELECTION_DESCRIPTION",electionDesc)
            fragment.arguments= args
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        // previous
        buttonPrev.setOnClickListener{
            val fragment = FragmentCreate()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val args = Bundle()
            args.putString("START_TIME",startTime)
            args.putString("END_TIME",endtime)
            args.putString("AGORA_ELECTION_TITLE",electionName)
            args.putString("AGORA_ELECTION_DESCRIPTION",electionDesc)
            fragment.arguments= args
            fragmentTransaction.replace(R.id.fragmentholder, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }
        startDate = this.input_start_date
        endDate=this.input_end_date
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
        startDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val timeSetListner = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    startDate!!.text =startDate!!.text.toString()+","+ SimpleDateFormat("HH:mm").format(cal.time)
                    val sdf= SimpleDateFormat("HH:MM:SS")
                    sdf.timeZone= TimeZone.getTimeZone("UTC")

                    startTime+=sdf.format(cal.time)+"Z"


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


        endDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val timeSetListner = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    endDate!!.text =endDate!!.text.toString()+","+ SimpleDateFormat("HH:mm").format(cal.time)
                    val sdf= SimpleDateFormat("HH:MM:SS")
                    sdf.timeZone= TimeZone.getTimeZone("UTC")
                    endtime+=sdf.format(cal.time)+"Z"
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
       startDate!!.text= sdf.format(cal.getTime())
       startTime=sdf2.format(cal.getTime())+'T'
    }

    private fun updateDateInView2() {

        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdf2=SimpleDateFormat(myFormat)
        sdf2.timeZone=TimeZone.getTimeZone("UTC")
        endDate!!.text=sdf.format(cal.getTime())
        endtime=sdf2.format(cal.getTime())
    }


}
