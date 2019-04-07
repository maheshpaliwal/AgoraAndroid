package com.maheshpaliwal.agora_android

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val userName: EditText = findViewById<EditText>(R.id.input_userName)
        val reset: Button = findViewById<Button>(R.id.btn_sendLink)
        reset.setOnClickListener{


            var error = false

            val in_userName = userName.getText().toString()


             // showing error when username is empty
            if (in_userName.isEmpty()) {
                userName.setError("This field can't be empty")
                error = true
            } else {
                userName.setError(null)
            }
            var dialog: ProgressDialog

            if (error == false) {


                dialog = ProgressDialog.show(this, null, null)

                dialog.setContentView(R.layout.loader_signin);




                dialog.show()


                val path = "api/v1/auth/forgotPassword/send/"+in_userName


                val url = "https://agora-rest-api.herokuapp.com/"


                // Post userName
                val params = HashMap<String,String>()
                params.put("userName", in_userName)


                val jsonObject = JSONObject(params)

                // Volley post request with userName
                val request = JsonObjectRequest(Request.Method.POST,url+path,jsonObject,
                    Response.Listener { response ->
                        // Process the json
                        try {
                            dialog.dismiss()

                                val intent = Intent(this@ForgotPasswordActivity, PasswordResetEmailSent::class.java)
                                startActivity(intent)





                        }catch (e:Exception){
                            // catching exception
                            dialog.dismiss()
                            Toast.makeText(this,"$response", Toast.LENGTH_LONG).show()



                        }

                    }, Response.ErrorListener{
                        // Error in request
                        dialog.dismiss()
                        if(it.toString().contains("com.android.volley.ParseError")){
                            val intent = Intent(this@ForgotPasswordActivity, PasswordResetEmailSent::class.java)
                            startActivity(intent)


                        }


                         else{


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
