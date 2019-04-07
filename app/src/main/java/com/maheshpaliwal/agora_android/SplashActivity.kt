package com.maheshpaliwal.agora_android

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import java.util.HashMap

class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //2 seconds
    var PREFS_NAME = "mypre"
    var PREF_USERNAME = "identifier"
    var PREF_PASSWORD = "password"

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    override fun onStart() {
        getUser()
        super.onStart()
    }
    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
    fun getUser() {
        val pref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val username = pref.getString(PREF_USERNAME, null)
        val password = pref.getString(PREF_PASSWORD, null)

        if (username != null || password != null) {
            val path = "api/v1/auth/login"


            val url = "https://agora-rest-api.herokuapp.com/"


            // Post parameters
            // Form fields and values
            val params = HashMap<String,String>()
            params.put("identifier", username)

            params.put("password", password)

            val jsonObject = JSONObject(params)

            // Volley post request with parameters
            val request = JsonObjectRequest(Request.Method.POST,url+path,jsonObject,
                Response.Listener { response ->
                    // Process the json
                    try {

                        val obj: JSONObject = response

                        val username:String=obj.getString("username")
                        val email:String=obj.getString("email")
                        val firstName:String=obj.getString("firstName")
                        val lastName:String=obj.getString("lastName")
                        var avtarURL:String?=obj.optString("avatarURL")
                        if(avtarURL==""){
                            avtarURL="https://agora-frontend.herokuapp.com/assets/agora.png"

                        }
                        val tokear: JSONObject =obj.getJSONObject("token")
                        val token:String=tokear.getString("token")
                        val length:Int=token.length
                        val expireson:String=tokear.getString("expiresOn")
                        val intent=Intent(this@SplashActivity,Main2Activity::class.java)
                        intent.putExtra("USER_NAME_AGORA",username)
                        intent.putExtra("EMAIL_AGORA",email)
                        intent.putExtra("FIRST_NAME_AGORA",firstName)
                        intent.putExtra("LAST_NAME_AGORA",lastName)
                        intent.putExtra("AVATAR_URL_AGORA",avtarURL)
                        intent.putExtra("TOKEN_AGORA",token)
                        intent.putExtra("EXPIRES_ON_AGORA",expireson)

                        startActivity(intent)
                        finish()




                    }catch (e:Exception){

                        Toast.makeText(this,"$e", Toast.LENGTH_LONG).show()



                    }

                }, Response.ErrorListener{
                    // Error in request




                    if(it.toString().contains("com.android.volley.AuthFailureError")){
                        displayToast("Username or Password is incorrect")


                    }
                    else{


                        Toast.makeText(this,"volley error: ${it.toString()}", Toast.LENGTH_LONG).show()}

                })


            // Volley request policy, only one time request to avoid duplicate transaction
            request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                // 0 means no retry
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )

            // Add the volley post request to the request queue
            VolleySingleton.getInstance(this).addToRequestQueue(request)

        }
    }
    private fun displayToast(message: String) {
        // Inflate toast XML layout
        val layout: View = layoutInflater.inflate(
            R.layout.toast_layout,
            findViewById<ViewGroup>(R.id.toast_layout_root)
        )
        // Fill in the message into the textview
        val text: TextView = layout.findViewById<TextView>(R.id.text)
        text.text = message
        // Construct the toast, set the view and display
        val toast = Toast(applicationContext)
        toast.view = layout
        toast.show()
    }
}
