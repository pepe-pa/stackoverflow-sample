package com.padamczyk.mobile.stackoverflow.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.padamczyk.mobile.stackoverflow.search.view.SearchActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, SearchActivity::class.java))
        finish()
    }
}
