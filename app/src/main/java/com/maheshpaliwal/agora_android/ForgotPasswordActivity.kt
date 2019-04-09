package com.maheshpaliwal.agora_android

import android.app.ProgressDialog
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
// Activity where user can reset his/her password

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val progressBar: ProgressBar =findViewById<ProgressBar>(R.id.progress_bar)
        val userName: EditText = findViewById<EditText>(R.id.input_userName)
        val reset: Button = findViewById<Button>(R.id.btn_sendLink)
        reset.setOnClickListener{
            // define error variable
            var error = false
            reset.visibility= View.GONE
            // showing rotating spinner
            progressBar.visibility= View.VISIBLE
            val inUserName = userName.getText().toString()
             // showing error when username is empty
            if (inUserName.isEmpty()) {
                userName.setError("This field can't be empty")
                error = true
            } else {
                userName.setError(null)
            }
            // if there is no error in user input
            if (error == false) {
                val path = "api/v1/auth/forgotPassword/send/"+inUserName
                val url = "https://agora-rest-api.herokuapp.com/"
                // Post userName
                val params = HashMap<String,String>()
                params.put("userName", inUserName)
                val jsonObject = JSONObject(params)
                // Volley post request with userName
                val request = JsonObjectRequest(Request.Method.POST,url+path,jsonObject,
                    Response.Listener { response ->
                        // Process the json
                        try {
                            progressBar.visibility=View.GONE
                            reset.visibility=View.VISIBLE
                            val intent = Intent(this@ForgotPasswordActivity, PasswordResetEmailSent::class.java)
                            startActivity(intent)
                        }catch (e:Exception){
                            progressBar.visibility=View.GONE
                            reset.visibility=View.VISIBLE
                            // catching exception
                            Toast.makeText(this,"$response", Toast.LENGTH_LONG).show()
                        }

                    }, Response.ErrorListener{
                        // Error in request
                        progressBar.visibility=View.GONE
                        reset.visibility=View.VISIBLE
                        if(it.toString().contains("com.android.volley.ParseError")){
                            val intent = Intent(this@ForgotPasswordActivity, PasswordResetEmailSent::class.java)
                            startActivity(intent)
                        }
                         else{
                            // displaying error in Toast
                            Toast.makeText(this,"volley error: ${it.toString()}", Toast.LENGTH_LONG).show()}

                    })


                // Volley request policy, only one time request to avoid duplicate transaction
                request.retryPolicy = DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    // 0 means no retry
                    0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                    1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )

                // Adding the volley post request to the request queue
                VolleySingleton.getInstance(this).addToRequestQueue(request)}

        }

    }
}
