package com.maheshpaliwal.agora_android


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_fragment_profile.*
import org.w3c.dom.Text
// Fragment Profile

class FragmentProfile : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // declare variables
        var userName:TextView=this.user_Name
        var Name:TextView=this.name
        var Email_add=this.email
        val profile_pic=this.profile_pic
        val username= arguments!!.getString("USER_NAME_AGORA")
        val firstName= arguments!!.getString("FIRST_NAME_AGORA")
        val lastName= arguments!!.getString("LAST_NAME_AGORA")
        val emailAdd= arguments!!.getString("EMAIL_AGORA")
        val avtarurl= arguments!!.getString("AVATAR_URL_AGORA")
        val token= arguments!!.getString("TOKEN_AGORA")
        val expires_on= arguments!!.getString("EXPIRES_ON_AGORA")
        Picasso.get().load(avtarurl).into(profile_pic)
        userName.text=username
        Name.text=firstName+lastName
        Email_add.text=emailAdd
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_fragment_profile, container, false)
    }
}
