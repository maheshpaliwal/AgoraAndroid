package com.maheshpaliwal.agora_android


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import kotlinx.android.synthetic.main.fragment_fragment_all.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

// Fragment for sshowing Finished Elections
class fragment_finished : Fragment() {
    var recyclerView: RecyclerView?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // initializing recycler view
        recyclerView=this.recycler_view
        // loading finished elections
        loadfinished()
        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_finished, container, false)
    }
    fun loadfinished(){
        //token
        val token= arguments!!.getString("TOKEN_AGORA")
        // path
        val path = "api/v1/election"
        //url
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
                        recyclerView.adapter=ElectionCardAdapter(elections)}


                }
                else{
                    val jar: JSONArray =response.getJSONArray("elections")
                    all=jar.length()
                    val elections= ArrayList<Election_info>()
                    for (i in 0.. jar.length()-1){
                        var arraY_inside: JSONObject =jar.getJSONObject(i)
                        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        startTime=arraY_inside.getString("start")
                        endTime=arraY_inside.getString("end")
                        var currentTime:Long=System.currentTimeMillis()
                        var timeStart: Date =format.parse(startTime)
                        var timeEnd: Date =format.parse(endTime)
                        var difference1:Long=timeStart.getTime()-currentTime
                        var difference2:Long=timeEnd.getTime()-currentTime
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
                            var candidate: JSONArray =arraY_inside.getJSONArray("candidates")
                            var l:Int=candidate.length()
                            var candidateString:String=""
                            for(l in 0.. candidate.length()-1){
                                candidateString+=candidate.getString(l)+"\n"

                            }
                            val election= Election_info(
                                "Election: "+arraY_inside.getString("name"),
                                arraY_inside.getString("description"),
                                candidateString,
                                ""+timeStart,status,
                                ""+timeEnd
                            )
                            elections.add(election)
                            val manager = LinearLayoutManager(context)
                            var recyclerView=this.recycler_view
                            if(recyclerView==null){
                            }
                            else{
                                recyclerView.layoutManager = manager
                                recyclerView.adapter=ElectionCardAdapter(elections)}
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
