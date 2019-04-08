package com.maheshpaliwal.agora_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PasswordResetEmailSent : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_password_reset_email_sent)


        val redirect_signIn:Button=findViewById<Button>(R.id.btn_signin)
        redirect_signIn.setOnClickListener{

            startActivity(Intent(this@PasswordResetEmailSent,SignInActivity::class.java))

        }
    }
}
