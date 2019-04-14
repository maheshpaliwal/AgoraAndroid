package com.maheshpaliwal.agora_android.fragments.electionFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.maheshpaliwal.agora_android.R
import com.maheshpaliwal.agora_android.volley.VolleySingleton
import com.maheshpaliwal.agora_android.adapter.ElectionCardAdapter
import com.maheshpaliwal.agora_android.model.Election_info
import kotlinx.android.synthetic.main.fragment_fragment_all.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*



class fragment_all : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // load all elections data
        loadAll()
        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_all, container, false)
    }
    fun loadAll(){
        val token= arguments!!.getString("TOKEN_AGORA")
        val path = "api/v1/election"
        val url = "https://agora-rest-api.herokuapp.com/"
        val request = object : JsonObjectRequest(Request.Method.GET, url+path, null,
            Response.Listener { response ->
                var startTime:String?=null
                var endTime:String?=null
                var all:Int=0
                var pending:Int=0
                var finished:Int=0
                var active:Int=0
                if(response.getJSONArray("elections").length()==0){
                    val elections=ArrayList<Election_info>()
                    val election=Election_info(
                        "Election: DEMO",
                        "You have not created any elections that's why you are seeing my demo ui for agora",
                        "You \n Mappie \n Karan",
                        "2019-04-25, 10:30:40 AM ",
                        "ACTIVE","2019-04-25, 10:30:40 AM"
                    )
                    elections.add(election)
                    val manager = LinearLayoutManager(context)
                    var recyclerView=this.recycler_view
                    if(recyclerView==null){}
                    else{
                        recyclerView.layoutManager = manager
                        recyclerView.adapter= ElectionCardAdapter(elections)
                    }
                }
                else{
                    val jar: JSONArray =response.getJSONArray("elections")
                    all=jar.length()
                    val elections=ArrayList<Election_info>()
                    for (i in 0.. jar.length()-1){
                        var arraY_inside: JSONObject =jar.getJSONObject(i)
                        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        startTime=arraY_inside.getString("start")
                        endTime=arraY_inside.getString("end")
                        var current_time:Long=System.currentTimeMillis()
                        var timeStart: Date =format.parse(startTime)
                        var timeEnd: Date =format.parse(endTime)
                        var difference1:Long=timeStart.getTime()-current_time
                        var difference2:Long=timeEnd.getTime()-current_time
                        var status:String=""
                        if(difference1>0&&difference2>0){
                            pending++
                            status="PENDING"
                        }
                        else if(difference1<=0&&difference2>=0){
                            active++
                            status="ACTIVE"
                        }
                        else if(difference1<0&&difference2<0){
                            finished++
                            status="FINISHED"
                        }
                        var candidate:JSONArray=arraY_inside.getJSONArray("candidates")
                        var l:Int=candidate.length()
                        var candidate_string:String=""
                        for(l in 0.. candidate.length()-1){
                            candidate_string+=candidate.getString(l)+"\n"

                        }
                        val election=Election_info(
                            "Election: "+arraY_inside.getString("name"),
                            arraY_inside.getString("description"),
                            candidate_string,
                            ""+timeStart,status,
                            ""+timeEnd
                        )
                        elections.add(election)
                        val manager = LinearLayoutManager(context)
                        var recyclerView=this.recycler_view
                        if(recyclerView==null){}
                        else{
                            recyclerView.layoutManager = manager
                            recyclerView.adapter= ElectionCardAdapter(elections)
                        }
                    }
                }

            }, Response.ErrorListener { error ->
                Toast.makeText(context,"$error", Toast.LENGTH_LONG).show()
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
        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            5, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        // Add the volley post request to the request queue
        context?.let { VolleySingleton.getInstance(it).addToRequestQueue(request) }
    }


}
