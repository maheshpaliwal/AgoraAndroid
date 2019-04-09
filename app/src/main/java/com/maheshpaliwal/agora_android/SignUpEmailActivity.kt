package com.maheshpaliwal.agora_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
// Account Activation instructions
class SignUpEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_email)
        val signIn: Button = findViewById<Button>(R.id.btn_signin)
        signIn.setOnClickListener{

            val intent = Intent(this@SignUpEmailActivity, SignInActivity::class.java)
            startActivity(intent)

        }
    }
}
