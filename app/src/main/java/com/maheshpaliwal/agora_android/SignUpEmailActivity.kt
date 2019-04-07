package com.maheshpaliwal.agora_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpEmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_email)
        val signin: Button = findViewById<Button>(R.id.btn_signin)
        signin.setOnClickListener{

            val intent = Intent(this@SignUpEmailActivity, SignInActivity::class.java)
            startActivity(intent)

        }
    }
}
