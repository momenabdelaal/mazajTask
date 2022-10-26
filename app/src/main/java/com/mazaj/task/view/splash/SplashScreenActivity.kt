package com.mazaj.task.view.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.mazaj.task.databinding.ActivitySplashBinding
import com.mazaj.task.view.utilties.Constants
import com.mazaj.task.view.utilties.MovementHelper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY: Long = 4000

    private lateinit var binding: ActivitySplashBinding
//    Configuration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        //setContentView(R.layout.activity_splash_screen)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupBoundsImage()


    }

    private fun startSplashTimer() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_DISPLAY)
                MovementHelper.startMainActivity(
                    this@SplashScreenActivity,
                    Constants.HOME_PAGE
                )
                this@SplashScreenActivity.finish()

        }
     }




    private fun setupBoundsImage() {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        //set animation for logo
        lifecycleScope.launch {
            val bounce: Animation =
                AnimationUtils.loadAnimation(this@SplashScreenActivity, android.R.anim.fade_in);
            bounce.also { binding.container.animation = it };
            delay(3000)
            startSplashTimer();

        }
    }

}