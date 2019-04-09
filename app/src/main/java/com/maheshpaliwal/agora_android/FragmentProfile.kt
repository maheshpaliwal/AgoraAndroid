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
        val user_name= arguments!!.getString("USER_NAME_AGORA")
        val first_name= arguments!!.getString("FIRST_NAME_AGORA")
        val last_name= arguments!!.getString("LAST_NAME_AGORA")
        val email_add= arguments!!.getString("EMAIL_AGORA")
        val avtar_url= arguments!!.getString("AVATAR_URL_AGORA")
        val token= arguments!!.getString("TOKEN_AGORA")
        val expires_on= arguments!!.getString("EXPIRES_ON_AGORA")
        Picasso.get().load(avtar_url).into(profile_pic)
        userName.text=user_name
        Name.text=first_name+last_name
        Email_add.text=email_add
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_fragment_profile, container, false)
    }
}
