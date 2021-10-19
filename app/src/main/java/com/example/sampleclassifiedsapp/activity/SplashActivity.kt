package com.example.sampleclassifiedsapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleclassifiedsapp.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAnimation();
    }

    private fun startAnimation() {
        val animation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.scale_animation)
        animation.setInterpolator(LinearInterpolator())
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }
            override fun onAnimationRepeat(animation: Animation) {
            }
            override fun onAnimationEnd(animation: Animation) {
                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })
        lLMain.startAnimation(animation)

    }



}