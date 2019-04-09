package com.maheshpaliwal.agora_android


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.view.ViewGroup
import android.widget.*
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

// Sign Up Activity

class SignUpActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_up)
    // Declare Variables
    val userName: EditText = findViewById<EditText>(R.id.input_userName)
    val firstName: EditText = findViewById<EditText>(R.id.input_firstName)
    val lastName: EditText = findViewById<EditText>(R.id.input_lastName)
    val progressBar: ProgressBar =findViewById<ProgressBar>(R.id.progress_bar)
    val email: EditText = findViewById<EditText>(R.id.input_email)
    val password: EditText = findViewById<EditText>(R.id.input_password)
    val signup: Button = findViewById<Button>(R.id.btn_signup)
    val login_activity: TextView = findViewById<TextView>(R.id.redirect_link_login)
    login_activity.setOnClickListener{
      val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
      startActivity(intent)
    }
    signup.setOnClickListener {
      var error = false
      val in_userName = userName.getText().toString()
      val in_firstName = firstName.getText().toString()
      val in_lastName = lastName.getText().toString()
      val in_email = email.getText().toString()
      val in_password = password.getText().toString()
      // showing appropriate erros
      if (in_password.isEmpty() || in_password.length < 8) {
        password.setError("at least 8 characters")
        error = true
      } else {
        password.setError(null)
      }
      if (in_userName.isEmpty()) {
        userName.setError("This field can't be empty")
        error = true
      } else {
        userName.setError(null)
      }
      if (in_firstName.isEmpty()) {
        firstName.setError("This field can't be empty")
        error = true
      } else {
        firstName.setError(null)
      }
      if (in_lastName.isEmpty()) {
        lastName.setError("This field can't be empty")
        error = true
      } else {
        lastName.setError(null)
      }
      if (in_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(in_email).matches()) {
        email.setError("enter a valid email address")
        error = true
      } else {
        email.setError(null)
      }
        // if there is no error in user input
      if (error == false) {
      signup.visibility=View.GONE
        progressBar.visibility=View.VISIBLE
        val path = "api/v1/auth/signup"
        val url = "https://agora-rest-api.herokuapp.com/"
        // Post parameters
        // Form fields and values
        val params = HashMap<String,String>()
        params.put("identifier", in_userName)
        params.put("firstName", in_firstName)
        params.put("lastName", in_lastName)
        params.put("email", in_email)
        params.put("password", in_password)
        val jsonObject = JSONObject(params)
        // Volley post request with parameters
        val request = JsonObjectRequest(Request.Method.POST,url+path,jsonObject,
          Response.Listener { response ->
            // Process the json
            try {
              progressBar.visibility=View.GONE
              signup.visibility=View.VISIBLE
              val intent = Intent(this@SignUpActivity, SignUpEmailActivity::class.java)
              startActivity(intent)

            }catch (e:Exception){
            }
          }, Response.ErrorListener{
            // Error in request
            if(it.toString().contains("com.android.volley.ParseError")){
                  progressBar.visibility=View.GONE
                  signup.visibility=View.VISIBLE
                  val intent = Intent(this@SignUpActivity, SignUpEmailActivity::class.java)
                  startActivity(intent)
            }
            else{
                  progressBar.visibility=View.GONE
                  signup.visibility=View.VISIBLE
                  displayToast("An Error occured!")}
          })
        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
          DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
          // 0 means no retry
          0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
          1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this).addToRequestQueue(request)
      }
    }
  }
  private fun displayToast(message: String) {
    // Inflate toast XML layout
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
}





