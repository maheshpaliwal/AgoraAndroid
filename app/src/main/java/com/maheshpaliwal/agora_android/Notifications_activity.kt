package com.maheshpaliwal.agora_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
// Notifications Activity
class Notifications_activity : AppCompatActivity() {
    // Declare Variables
    val manager = supportFragmentManager
    var userName: String? = null
    var firstName: String? = null
    var lastName: String?=null
    var emailAdd: String?=null
    var avtarUrl: String?=null
    var token: String?=null
    var expiresOn: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications_activity)
        val extras = intent.extras
        userName = extras.getString("USER_NAME_AGORA")
        firstName=extras.getString("FIRST_NAME_AGORA")
        lastName=extras.getString("LAST_NAME_AGORA")
        emailAdd=extras.getString("EMAIL_AGORA")
        avtarUrl=extras.getString("AVATAR_URL_AGORA")
        token=extras.getString("TOKEN_AGORA")
        expiresOn=extras.getString("EXPIRES_ON_AGORA")
        val backButton:Button=findViewById<Button>(R.id.back_button)
        // back button
        backButton.setOnClickListener{
            val intent= Intent(this@Notifications_activity,Main2Activity::class.java)
            intent.putExtra("USER_NAME_AGORA",userName)
            intent.putExtra("EMAIL_AGORA",emailAdd)
            intent.putExtra("FIRST_NAME_AGORA",firstName)
            intent.putExtra("LAST_NAME_AGORA",lastName)
            intent.putExtra("AVATAR_URL_AGORA",avtarUrl)
            intent.putExtra("TOKEN_AGORA",token)
            intent.putExtra("EXPIRES_ON_AGORA",expiresOn)
            startActivity(intent)
            startActivity(intent)
        }
    }
}
