package com.ci.notesapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.ci.notesapp.R
import com.ci.notesapp.base.BaseActivity
import com.ci.notesapp.ui.noteslist.NotesListActivity

class SplashScreen : BaseActivity() {
    override fun setLayout(): Int {
        makeFullScreen()
        return R.layout.activity_splash_screen
    }

    override fun initView(savedInstanceState: Bundle?) {
        val handler = Handler()
        val delayrunnable = Runnable {

            startActivity(Intent(this@SplashScreen, NotesListActivity::class.java))
            finish()
        }
        handler.postDelayed(delayrunnable, 3000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.splash)
        val splash =
            findViewById<View>(R.id.imageView) as ImageView
        splash.startAnimation(animation)

//        splash.setColorFilter(getResources().getColor(R.color.white))
    }
}
