package com.maheshpaliwal.agora_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
// Notifications Activity
class Notifications_activity : AppCompatActivity() {
    // Declare Variables
    val manager = supportFragmentManager
    var user_name: String? = null
    var first_name: String? = null
    var last_name: String?=null
    var email_add: String?=null
    var avtar_url: String?=null
    var token: String?=null
    var expires_on: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications_activity)
        val extras = intent.extras
        user_name = extras.getString("USER_NAME_AGORA")
        first_name=extras.getString("FIRST_NAME_AGORA")
        last_name=extras.getString("LAST_NAME_AGORA")
        email_add=extras.getString("EMAIL_AGORA")
        avtar_url=extras.getString("AVATAR_URL_AGORA")
        token=extras.getString("TOKEN_AGORA")
        expires_on=extras.getString("EXPIRES_ON_AGORA")
        val back_button:Button=findViewById<Button>(R.id.back_button)
        // back button
        back_button.setOnClickListener{
            val intent= Intent(this@Notifications_activity,Main2Activity::class.java)
            intent.putExtra("USER_NAME_AGORA",user_name)
            intent.putExtra("EMAIL_AGORA",email_add)
            intent.putExtra("FIRST_NAME_AGORA",first_name)
            intent.putExtra("LAST_NAME_AGORA",last_name)
            intent.putExtra("AVATAR_URL_AGORA",avtar_url)
            intent.putExtra("TOKEN_AGORA",token)
            intent.putExtra("EXPIRES_ON_AGORA",expires_on)
            startActivity(intent)
            startActivity(intent)
        }
    }
}
