package com.maheshpaliwal.agora_android


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main2.*

import kotlinx.android.synthetic.main.app_bar_main2.*
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_mainapp.*
import android.text.method.TextKeyListener.clear
import android.content.SharedPreferences



// Main Activity of agora vote contains navigation drawer & bottom navigation
class Main2Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    // declare variables
    val manager = supportFragmentManager
    var userName: String? = null
    var firstName: String? = null
    var lastName: String?=null
    var emailAdd: String?=null
    var avtarUrl: String?=null
    var token: String?=null
    var expiresOn: String?=null
    var PREFS_NAME = "mypre"
    var PREF_USERNAME = "identifier"
    var PREF_PASSWORD = "password"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard-> {
                FragmentOne()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_create_election->{
                FragmentFive()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_elections-> {
                FragmentSix()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile-> {
                FragmentFour()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        // getting saved instances
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {

            } else {
                userName = extras.getString("USER_NAME_AGORA")
                firstName=extras.getString("FIRST_NAME_AGORA")
                lastName=extras.getString("LAST_NAME_AGORA")
                emailAdd=extras.getString("EMAIL_AGORA")
                avtarUrl=extras.getString("AVATAR_URL_AGORA")
                token=extras.getString("TOKEN_AGORA")
                expiresOn=extras.getString("EXPIRES_ON_AGORA")

            }
        } else {
            userName = savedInstanceState.getSerializable("USER_NAME_AGORA") as String
            firstName=savedInstanceState.getSerializable("FIRST_NAME_AGORA") as String
            lastName=savedInstanceState.getSerializable("LAST_NAME_AGORA") as String
            emailAdd=savedInstanceState.getSerializable("EMAIL_AGORA") as String
            avtarUrl=savedInstanceState.getSerializable("AVATAR_URL_AGORA") as String
            token=savedInstanceState.getSerializable("TOKEN_AGORA") as String
            expiresOn=savedInstanceState.getSerializable("EXPIRES_ON_AGORA") as String
        }
        // dashboard as default fragment
        FragmentOne()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val headerView:View = navigationView.getHeaderView(0)
        val navEmail:TextView = headerView.findViewById<TextView>(R.id.email_header)
        navEmail.text=emailAdd
        val navUsername:TextView=headerView.findViewById<TextView>(R.id.user_name_navigation)
        navUsername.text=userName
        val nav_profile:ImageView=headerView.findViewById<ImageView>(R.id.imageView)
        Picasso.get().load(avtarUrl).into(nav_profile)
        val notifications_button:ImageView=findViewById<ImageView>(R.id.notification_action_bar)
        notifications_button.setOnClickListener{
            val intent=Intent(this@Main2Activity,Notifications_activity::class.java)
            intent.putExtra("USER_NAME_AGORA",userName)
            intent.putExtra("EMAIL_AGORA",emailAdd)
            intent.putExtra("FIRST_NAME_AGORA",firstName)
            intent.putExtra("LAST_NAME_AGORA",lastName)
            intent.putExtra("AVATAR_URL_AGORA",avtarUrl)
            intent.putExtra("TOKEN_AGORA",token)
            intent.putExtra("EXPIRES_ON_AGORA",expiresOn)
            startActivity(intent)

        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.navigation_cast_vote->{
                FragmentEight()
            }
            R.id.nav_about-> {
                FragmentTwo()
            }
            R.id.nav_help -> {
                FragmentThree()
            }
            R.id.nav_logout -> {
                val pref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                val editor = pref.edit()
                editor.clear()
                editor.commit()
                startActivity(Intent(this@Main2Activity,SignInActivity::class.java))
            }
            R.id.nav_share -> {

            }
            R.id.nav_feedback -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    // Dashboard Fragment
    fun FragmentOne(){
        val transaction = manager.beginTransaction()
        val fragment= FragmentDashboard()
        val args = Bundle()
        // sending arguments
        args.putString("USER_NAME_AGORA",userName)
        args.putString("EMAIL_AGORA",emailAdd)
        args.putString("FIRST_NAME_AGORA",firstName)
        args.putString("LAST_NAME_AGORA",lastName)
        args.putString("AVATAR_URL_AGORA",avtarUrl)
        args.putString("TOKEN_AGORA",token)
        args.putString("EXPIRES_ON_AGORA",expiresOn)
        fragment.arguments= args
        transaction.replace(R.id.fragmentholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // Fragment Abput
    fun FragmentTwo(){
        val transaction = manager.beginTransaction()
        val fragment= FragmentAbout()
        val args = Bundle()
        args.putString("USER_NAME_AGORA",userName)
        args.putString("EMAIL_AGORA",emailAdd)
        args.putString("FIRST_NAME_AGORA",firstName)
        args.putString("LAST_NAME_AGORA",lastName)
        args.putString("AVATAR_URL_AGORA",avtarUrl)
        args.putString("TOKEN_AGORA",token)
        args.putString("EXPIRES_ON_AGORA",expiresOn)
        fragment.arguments= args
        transaction.replace(R.id.fragmentholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // Fragment Help
    fun FragmentThree(){
        val transaction = manager.beginTransaction()
        val fragment= FragmentHelp()
        transaction.replace(R.id.fragmentholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // Fragment Profile
    fun FragmentFour(){
        val transaction = manager.beginTransaction()
        val fragment= FragmentProfile()
        val args = Bundle()
        args.putString("USER_NAME_AGORA",userName)
        args.putString("EMAIL_AGORA",emailAdd)
        args.putString("FIRST_NAME_AGORA",firstName)
        args.putString("LAST_NAME_AGORA",lastName)
        args.putString("AVATAR_URL_AGORA",avtarUrl)
        args.putString("TOKEN_AGORA",token)
        args.putString("EXPIRES_ON_AGORA",expiresOn)
        fragment.arguments= args
        transaction.replace(R.id.fragmentholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // Fragment create elections
    fun FragmentFive(){
        val transaction = manager.beginTransaction()
        val fragment= FragmentCreate()
        val args = Bundle()
        args.putString("USER_NAME_AGORA",userName)
        args.putString("EMAIL_AGORA",emailAdd)
        args.putString("FIRST_NAME_AGORA",firstName)
        args.putString("LAST_NAME_AGORA",lastName)
        args.putString("AVATAR_URL_AGORA",avtarUrl)
        args.putString("TOKEN_AGORA",token)
        args.putString("EXPIRES_ON_AGORA",expiresOn)
        fragment.arguments= args
        transaction.replace(R.id.fragmentholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    // fragment elections
    fun FragmentSix(){
        val transaction = manager.beginTransaction()
        val fragment= FragmentElection()
        val args = Bundle()
        args.putString("USER_NAME_AGORA",userName)
        args.putString("EMAIL_AGORA",emailAdd)
        args.putString("FIRST_NAME_AGORA",firstName)
        args.putString("LAST_NAME_AGORA",lastName)
        args.putString("AVATAR_URL_AGORA",avtarUrl)
        args.putString("TOKEN_AGORA",token)
        args.putString("EXPIRES_ON_AGORA",expiresOn)
        fragment.arguments= args
        transaction.replace(R.id.fragmentholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // Fragment Active elections for user
    fun FragmentEight(){
        val transaction = manager.beginTransaction()
        val fragment= Active_elections_for_user()
        transaction.replace(R.id.fragmentholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}
