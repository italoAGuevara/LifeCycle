package com.practice.lifecycle

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var mediaPlayer : MediaPlayer? = null
    private var position : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.btnCheck).setOnClickListener {
            startActivity(Intent(this, DialogActivity:: class.java))
        }

        Log.i("LifeCycle","OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("LifeCycle","onStart")
        mediaPlayer = MediaPlayer.create(this, R.raw.ai_2 )
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.seekTo( position )
        mediaPlayer?.start()
        Log.i("LifeCycle","onResume")
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
        if(mediaPlayer != null)
            position = mediaPlayer!!.currentPosition
    }

    override fun onStop() {
        super.onStop()
        Log.i("LifeCycle","onStop")
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LifeCycle","onDestroy")
    }
}