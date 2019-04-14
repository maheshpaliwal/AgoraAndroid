package com.maheshpaliwal.agora_android.fragments.introFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maheshpaliwal.agora_android.R

// App Intro Slide 1
class fragment_slide_1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.agora_welcome_screen1, container, false)

    }


}
