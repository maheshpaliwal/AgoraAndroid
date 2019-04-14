package com.maheshpaliwal.agora_android.fragments.mainActivityFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.fragment_fragment_dashboard.*
import org.json.JSONObject
import com.android.volley.AuthFailureError
import com.facebook.shimmer.ShimmerFrameLayout
import com.maheshpaliwal.agora_android.R
import com.maheshpaliwal.agora_android.volley.VolleySingleton
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*

// Dashboard Fragment
class FragmentDashboard : Fragment() {
    // Shimmer animation
    var mShimmerViewContainer:ShimmerFrameLayout? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // declare variables
        //token
        val token= arguments!!.getString("TOKEN_AGORA")
        mShimmerViewContainer = this.shimemer_view_container
        val allElection:TextView=this.count
        val pendingElection:TextView=this.count2
        val activeElection:TextView=this.count3
        val finishedElection:TextView=this.count4
        val text:TextView=this.total_finished
        val path = "api/v1/election"
        val url = "https://agora-rest-api.herokuapp.com/"
        val request = object : JsonObjectRequest(Request.Method.GET, url+path, null,
            Response.Listener { response ->
                var start_time:String?=null
                var end_time:String?=null
                var all:Int=0
                var pending:Int=0
                var finished:Int=0
                var active:Int=0
                if(response.getJSONArray("elections").length()==0){
                   allElection.text=""+0
                   pendingElection.text=""+0
                   activeElection.text=""+0
                   finishedElection.text=""+0
                   mShimmerViewContainer!!.stopShimmer()
                   mShimmerViewContainer!!.visibility=View.GONE
               }
                else{
                    val jar:JSONArray=response.getJSONArray("elections")
                    all=jar.length()
                for (i in 0.. jar.length()-1){
                    var arraY_inside: JSONObject =jar.getJSONObject(i)
                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    start_time=arraY_inside.getString("start")
                    end_time=arraY_inside.getString("end")
                    var currentTime:Long=System.currentTimeMillis()
                    var timeStart:Date=format.parse(start_time)
                    var timeEnd:Date=format.parse(end_time)
                    var difference1:Long=timeStart.getTime()-currentTime
                    var difference2:Long=timeEnd.getTime()-currentTime
                    if(difference1>0&&difference2>0){
                        pending++
                    }
                    else if(difference1<=0&&difference2>=0){
                        active++
                    }
                    else if(difference1<0&&difference2<0){
                        finished++
                    }
                }
                    mShimmerViewContainer!!.stopShimmer()
                    mShimmerViewContainer!!.visibility=View.GONE
                    allElection.text=""+all
                    pendingElection.text=""+pending
                    activeElection.text=""+active
                    finishedElection.text=""+finished
                }
            }, Response.ErrorListener { error ->
                //showing errors
                Toast.makeText(context,"$error",Toast.LENGTH_LONG).show()
            }) {
            /**
             * Passing some request headers
             */
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                //headers.put("Content-Type", "application/json");
                headers["X-Auth-Token"] = token
                return headers
            }
        }

        // Volley post request with parameters
        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            2, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Add the volley post request to the request queue
        context?.let { VolleySingleton.getInstance(it).addToRequestQueue(request) }
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_dashboard, container, false)


    }

    override fun onResume() {
        super.onResume()
        //start shimmer animation
        mShimmerViewContainer!!.startShimmer()
    }

    override fun onPause() {
        // stop shimmer animation
        super.onPause()
        mShimmerViewContainer!!.stopShimmer()
    }
}
