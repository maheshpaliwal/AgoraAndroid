package com.maheshpaliwal.agora_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.paolorotolo.appintro.AppIntro
import android.graphics.Color.parseColor
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage



class IntroActivity : AppIntro() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        val fragment1=fragment_slide_1()
        val fragment2=fragment_slide_2()
        val fragment3=fragment_slide_3()
        val fragment4=fragment_slide_4()
        addSlide(fragment1)
        addSlide(fragment2)
        addSlide(fragment3)
        addSlide(fragment4)

        // Instead of fragments, you can also use our default slide.
        // Just create a `SliderPage` and provide title, description, background and image.
        // AppIntro will do the rest.


        // OPTIONAL METHODS
        // Override bar/separator color.


        // Hide Skip/Done button.
        showSkipButton(true)



    }

    override fun onSkipPressed(currentFragment: Fragment) {

        super.onSkipPressed(currentFragment)
        finish()
        // Do something when users tap on Skip button.
    }

    override fun onDonePressed(currentFragment: Fragment) {

        super.onDonePressed(currentFragment)
        finish()
        // Do something when users tap on Done button.
    }




}
