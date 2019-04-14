package com.maheshpaliwal.agora_android

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import android.widget.Toast
import android.widget.TextView
import android.view.ViewGroup
import android.preference.PreferenceManager
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import com.android.volley.AuthFailureError
import com.facebook.CallbackManager
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.maheshpaliwal.agora_android.volley.VolleySingleton
import java.util.*

// Sign In activty
class SignInActivity : AppCompatActivity() {
    //declare variables
    private val EMAIL = "email"
    // preference name to save details
    var PREFS_NAME = "mypre"
    var PREF_USERNAME = "identifier"
    var PREF_PASSWORD = "password"
    var callbackManager = CallbackManager.Factory.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        //define variables
        val userName: EditText = findViewById<EditText>(R.id.input_userName)
        val password: EditText = findViewById<EditText>(R.id.input_password)
        val signIn: Button = findViewById<Button>(R.id.btn_signin)
        val progressBar: ProgressBar =findViewById<ProgressBar>(R.id.progress_bar)
        val resetPassword:TextView=findViewById<TextView>(R.id.forgot_password)
        val buttonFb:Button=findViewById<Button>(R.id.login_button_fb)
        val showPassword:ImageButton=findViewById<ImageButton>(R.id.show_password)
        val hidePassword:ImageButton=findViewById<ImageButton>(R.id.hide_password)
        val signup:TextView=findViewById<TextView>(R.id.btn_signup)
        showPassword.setOnClickListener{
            // Show Password
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
            hidePassword.visibility=View.VISIBLE
            showPassword.visibility=View.GONE

        }
        hidePassword.setOnClickListener{
            // hide password
            password.setTransformationMethod(PasswordTransformationMethod.getInstance())
            hidePassword.visibility=View.GONE
            showPassword.visibility=View.VISIBLE


        }

        val t = Thread(Runnable {
            //  Initialize SharedPreferences
            val getPrefs = PreferenceManager
                .getDefaultSharedPreferences(baseContext)
            //  Create a new boolean and preference and set it to true
            val isFirstStart = getPrefs.getBoolean("firstStart", true)
            //  If the activity has never started before...
            if (isFirstStart) {
                //  Launch app intro
                val i = Intent(this@SignInActivity, IntroActivity::class.java)
                runOnUiThread { startActivity(i) }
                //  Make a new preferences editor
                val e = getPrefs.edit()
                //  Edit preference to make it false because we don't want this to run again
                e.putBoolean("firstStart", false)
                //  Apply changes
                e.apply()
            }
        })


        t.start()
        buttonFb.setOnClickListener{
            //facebook sign In
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        var accesstoken=loginResult.accessToken.token.toString()
                        val path = "api/v1/auth/authenticate/facebook"
                        val url = "https://agora-rest-api.herokuapp.com/"
                        val request = object : JsonObjectRequest(Request.Method.GET, url+path, null,
                            Response.Listener { response ->
                                Toast.makeText(applicationContext,"$response",Toast.LENGTH_LONG).show()
                            }, Response.ErrorListener { error ->
                                Toast.makeText(applicationContext,"$error",Toast.LENGTH_LONG).show()
                            }) {
                            /**
                             * Passing some request headers
                             */
                            @Throws(AuthFailureError::class)
                            override fun getHeaders(): Map<String, String> {
                                val headers = HashMap<String, String>()
                                //headers.put("Content-Type", "application/json");
                                headers["Access-Token"] = accesstoken
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
                         VolleySingleton.getInstance(applicationContext).addToRequestQueue(request)


                    }

                    override fun onCancel() {
                        Log.d("Sign In", "Facebook onCancel.")

                    }

                    override fun onError(error: FacebookException) {
                        Toast.makeText(applicationContext,"$error",Toast.LENGTH_LONG).show()
                        Log.d("SignIN", "Facebook onError.")

                    }
                })
        }


     resetPassword.setOnClickListener{
        // redirect to reset password

         val intent=Intent(this@SignInActivity,ForgotPasswordActivity::class.java)
         startActivity(intent)
     }
        signup.setOnClickListener{
            val intent=Intent(this@SignInActivity,SignUpActivity::class.java)
            startActivity(intent)

        }
        signIn.setOnClickListener{
            // verify user credentials
            var error = false
            val in_userName = userName.getText().toString()
            val in_password = password.getText().toString()
            // show appropriate errors
            if (in_password.isEmpty()) {
                password.setError("This field can't be empty")

                error = true
            } else {
                password.setError(null)
            }
            // show appropriate errors
            if (in_userName.isEmpty()) {
                userName.setError("This field can't be empty")
                error = true
            } else {
                userName.setError(null)
            }
            // if there is no error in user input
            if (error == false) {
                signIn.visibility= View.GONE
                progressBar.visibility=View.VISIBLE
                val path = "api/v1/auth/login"
                val url = "https://agora-rest-api.herokuapp.com/"
                // Post parameters
                // Form fields and values
                val params = HashMap<String,String>()
                params.put("identifier", in_userName)
                params.put("password", in_password)
                val jsonObject = JSONObject(params)
                // Volley post request with parameters
                val request = JsonObjectRequest(Request.Method.POST,url+path,jsonObject,
                    Response.Listener { response ->
                        // Process the json
                        try {
                            progressBar.visibility=View.GONE
                            signIn.visibility=View.VISIBLE
                            val obj:JSONObject = response
                            val username:String=obj.getString("username")
                            val email:String=obj.getString("email")
                            val firstName:String=obj.getString("firstName")
                            val lastName:String=obj.getString("lastName")
                            var avtarURL:String?=obj.optString("avatarURL")
                            rememberMe(in_userName,in_password)
                            if(avtarURL==""){
                                avtarURL="https://agora-frontend.herokuapp.com/assets/agora.png"

                            }
                            val tokear:JSONObject=obj.getJSONObject("token")
                            val token:String=tokear.getString("token")
                            val length:Int=token.length
                            val expireson:String=tokear.getString("expiresOn")
                            val intent=Intent(this@SignInActivity,Main2Activity::class.java)
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
                            progressBar.visibility=View.GONE
                            signIn.visibility=View.VISIBLE
                            Toast.makeText(this,"$e",Toast.LENGTH_LONG).show()
                        }

                    }, Response.ErrorListener{
                        // Error in request
                        progressBar.visibility=View.GONE
                        signIn.visibility=View.VISIBLE
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
                VolleySingleton.getInstance(this).addToRequestQueue(request)}
        }
    }

    override fun onStart() {
        getUser()
        super.onStart()
    }

    private fun displayToast(message: String) {
        // display appropriate error
        val layout:View = layoutInflater.inflate(
            R.layout.toast_layout,
            findViewById<ViewGroup>(R.id.toast_layout_root)
        )
        // Fill in the message into the textview
        val text:TextView = layout.findViewById<TextView>(R.id.text)
        text.text = message
        // Construct the toast, set the view and display
        val toast = Toast(applicationContext)
        toast.view = layout
        toast.show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
    fun getUser() {
        // auto sign in
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
                        val obj:JSONObject = response
                        val username:String=obj.getString("username")
                        val email:String=obj.getString("email")
                        val firstName:String=obj.getString("firstName")
                        val lastName:String=obj.getString("lastName")
                        var avtarURL:String?=obj.optString("avatarURL")
                        if(avtarURL==""){
                            avtarURL="https://agora-frontend.herokuapp.com/assets/agora.png"

                        }
                        val tokear:JSONObject=obj.getJSONObject("token")
                        val token:String=tokear.getString("token")
                        val length:Int=token.length
                        val expireson:String=tokear.getString("expiresOn")
                        val intent=Intent(this@SignInActivity,Main2Activity::class.java)
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
                        // catch Exception if any
                    }

                }, Response.ErrorListener{
                    // Error in request
                    if(it.toString().contains("com.android.volley.AuthFailureError")){
                        displayToast("Username or Password is incorrect")
                    }
                    else{
                        displayToast("error occured")

                        }
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

    fun rememberMe(user: String, password: String) {
        //save username and password in SharedPreferences
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(PREF_USERNAME, user)
            .putString(PREF_PASSWORD, password)
            .commit()
    }
}
