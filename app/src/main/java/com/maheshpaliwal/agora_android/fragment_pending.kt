package com.maheshpaliwal.agora_android


import android.content.Context
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
import com.maheshpaliwal.agora_android.model.Election_info

import org.json.JSONArray
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.fragment_fragment_pending.*


class fragment_pending : Fragment() {
    var recyclerView: RecyclerView?=null

    override fun onActivityCreated(savedInstanceState: Bundle?) {

          recyclerView=this.recycler_view


        loadpending()




        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_pending, container, false)
    }
    fun loadpending(){



        val token= arguments!!.getString("TOKEN_AGORA")

        val path = "api/v1/election"


        val url = "https://agora-rest-api.herokuapp.com/"








        val jsonObject = JSONObject()
        val request: JsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url+path, null,
            Response.Listener { response ->

                var start_time:String?=null
                var end_time:String?=null
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
                        recyclerView.adapter=ElectionCardAdapter(elections)}



                }
                else{
                    val jar: JSONArray =response.getJSONArray("elections")
                    all=jar.length()
                    val elections= ArrayList<Election_info>()
                    for (i in 0.. jar.length()-1){

                        var arraY_inside: JSONObject =jar.getJSONObject(i)
                        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        start_time=arraY_inside.getString("start")

                        end_time=arraY_inside.getString("end")
                        var current_time:Long=System.currentTimeMillis()
                        var time_start: Date =format.parse(start_time)
                        var time_end: Date =format.parse(end_time)
                        var difference1:Long=time_start.getTime()-current_time
                        var difference2:Long=time_end.getTime()-current_time
                        var status:String=""
                        if(difference1>0&&difference2>0){
                            pending++
                            status="PENDING"
                            var candidate: JSONArray =arraY_inside.getJSONArray("candidates")
                            var l:Int=candidate.length()
                            var candidate_string:String=""
                            for(l in 0.. candidate.length()-1){
                                candidate_string+=candidate.getString(l)+"\n"

                            }
                            val election= Election_info(
                                "Election: "+arraY_inside.getString("name"),
                                arraY_inside.getString("description"),
                                candidate_string,
                                ""+time_start,status,
                                ""+time_end




                            )
                            if(pending==0){

                            }
                            else{
                            elections.add(election)
                            val manager = LinearLayoutManager(context)
                            var recyclerView=this.recycler_view
                            if(recyclerView==null){

                            }
                            else{
                                recyclerView.layoutManager = manager
                                recyclerView.adapter=ElectionCardAdapter(elections)}}




                        }
                        else if(difference1<=0&&difference2>=0){
                            active++
                            status="ACTIVE"


                        }
                        else if(difference1<0&&difference2<0){

                            finished++
                            status="FINISHED"

                        }


















                    }

                    // val date1 = SimpleDateFormat("yyyy-mm-ddThh:mm:ss.fffZ").parse(start_time)
                    val length:Int=jar.length()
                    val dtStart = "2010-10-15T09:27:37Z"
                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    var start_date: Date?=null
                    var end_date: Date?=null
                    var difference_date:Long?=null
                    try {
                        start_date = format.parse(start_time)
                        end_date=format.parse(end_time)
                        difference_date=end_date.getTime()-start_date.getTime()


                    } catch (e: ParseException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
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
